package com.camline;

import java.io.File;

import java.util.Date;

import com.odevis.pks.NoConnectionException;
import com.odevis.pks.protocoladapter.AbstractProtocolAdapter;
import com.odevis.pks.protocoladapter.pafile.*;
import com.odevis.pks.protocoladapter.pafile.config.PAFileType;
import com.odevis.pks.tooldriver.IToolDriver;

public abstract class PAFileWithoutDate extends AbstractProtocolAdapter implements IPAFile {

  private String m_sourceDirectory = null;
  private String m_destDirectory = null;
  private int counterForEmptyFolders;
  private PAFileType m_paConfig = null;
  private PAFilenameFilter m_filter = null;
  private Thread.UncaughtExceptionHandler m_uncaughtExceptionHandler = null;

  private FileInterface m_fileInterface = null;

  /**
   * @param driver
   * @param eventDriver
   */
  public PAFileWithoutDate(String paName, IToolDriver toolDriver, String sourceDirectory, String destDirectory, int counterForPollingEmptyFolders,PAFileType paConfig) {
    super(paName, toolDriver, PAFileWithoutDate.class);

    m_log.debug("PAFileWithoutDate() - start");
    m_sourceDirectory = sourceDirectory;
    m_destDirectory = destDirectory;
    this.counterForEmptyFolders = counterForPollingEmptyFolders;
    m_paConfig = paConfig;

    m_filter = new PAFilenameFilter(paConfig.getFilter());
    m_fileInterface = new FileInterface(m_paConfig.isWriteStackTraceToFile());

    m_log.debug("PAFileWithoutDate() - end");
  }

  /**
   * @param driver
   * @param eventDriver
   */
  public PAFileWithoutDate(String paName, IToolDriver toolDriver, String sourceDirectory, String destDirectory,
      int counterForPollingEmtyFolders ,PAFileType paConfig, Thread.UncaughtExceptionHandler eh) {
    this(paName, toolDriver, sourceDirectory, destDirectory, counterForPollingEmtyFolders,paConfig);
    m_uncaughtExceptionHandler = eh;
  }

  /**
   * @return the version of the tool driver.
   */
  @Override
	public String getVersion() {
    return Version.VERSION;
  }

  /**
   * Initialize the protocol adapter and do a connect to the equipment
   * 
   */
  @Override
  protected void init() throws NoConnectionException {
    m_log.debug("init() - start");
    connect();
    m_log.debug("init() - end");
  }

  /**
   * Checks if the given directory exists
   * 
   * @param source
   *          directory the name of the directory to be checked
   * @return true, if the directory does exist; false otherwise
   */
  @Override
  public void connect() throws NoConnectionException {
    m_log.debug("connect() - start");

    File f = new File(m_sourceDirectory);
    if (!f.isDirectory()) {
      m_driver.getAlarmDriver().dispatchAlarm(PAConstants.ALID_NO_SHARED_DIR,
          "Source directory not found: " + m_sourceDirectory, 10);

      m_bConnected = false;
      m_driver.onDisconnect(this);

      throw new NoConnectionException("Source directory not found: " + m_sourceDirectory);
    }

    m_bConnected = true;

    try {
      m_fileInterface.createLocalDirs(m_destDirectory, "", "");
      m_driver.onConnect(this);
    }
    catch (CreateDirectoryException cde) {
      m_log.fatal("Error in createLocalDirs: " + cde, cde);

      m_driver.getAlarmDriver().dispatchAlarm(PAConstants.ALID_CANT_CREATE_DIRS,
          "Error in createLocalDirs: " + cde.getLocalizedMessage(), 10);

      m_bConnected = false;
      m_driver.onDisconnect(this);

      throw new NoConnectionException("Please check previous error messages and then start driver again");
    }
    m_log.debug("connect() - end");
  }

  /**
   * Disconnect from the equipment, here is nothing to do because we have a
   * network share
   */
  @Override
  protected void disconnect() {
    m_log.debug("disconnect() - start");
    m_bConnected = false;
    m_driver.onDisconnect(this);
    m_log.debug("disconnect() - end");
  }

  /**
   * Start the thread who polls for new files from the equipment
   */
  @Override
  public void start() throws NoConnectionException {
    m_log.debug("start() - start");

    try {
      init();
    }
    catch (NoConnectionException nce) {
      m_log.error("Exception: " + nce, nce);
    }

    // Start a new thread and calls the run method
    m_thread = new Thread(this);
    // Give Thread a unique name for logging purposes
    m_thread.setName(m_paConfig.getSubDirectory());
    m_thread.start();

    if (m_uncaughtExceptionHandler != null) {
      m_thread.setUncaughtExceptionHandler(m_uncaughtExceptionHandler);
    }

    m_log.debug("start() - end");
  }

  /**
   * This thread connects to the tool if necessary and polls for new files. Each
   * file is processed and moved from the equipments shared directory. The
   * tracking and sensor data then is send to EcoFrame.
   * 
   * @see com.odevis.pks.protocoladapter.AbstractProtocolAdapter#run()
   */
  @Override
  public void run() throws IllegalThreadStateException {
    
    String thrdname = "(Thread " + m_thread.getName() + ")";
    m_log.debug(thrdname + " run() - start");

    m_fileInterface.setThreadName(m_thread.getName());

    while (m_bStopThread == false) {
      // Remember start time
      long lTime1 = new Date().getTime();
      if (m_bConnected == false) {
        try {
          connect();
        }
        catch (NoConnectionException nce) {
          m_log.error(thrdname + " " + nce, nce);
        }
      }
      else {
        String subDirectoryPath = m_sourceDirectory + File.separator + m_paConfig.getSubDirectory();
        try {
          // Read files from directory ordered by name
          Integer paging = m_paConfig.getPagingSize();
          int pagingSize = 0;
          if (paging != null) {
            pagingSize = paging.intValue();
          }

          File[] fileList = m_fileInterface.getFilelist(subDirectoryPath, m_filter, pagingSize);
          if (fileList == null) {
            continue;
          }
          for (int i = 0; i < fileList.length; i++) {
            ParseFileResult parseResult = new ParseFileResult();
            // first parse the file
            try {
              parseFile(subDirectoryPath + File.separator + fileList[i].getName());

              m_log.info("file " + fileList[i].getName() + " successfully parsed");
            }
            catch (ParseFileException pfe) {
              m_log.error(thrdname + " " + pfe, pfe);

              parseResult.setParseError(pfe);
              m_driver.getAlarmDriver().dispatchAlarm(PAConstants.ALID_PARSE_ERROR, pfe.getMessage(), 10);
            }
            catch (PKSException pkse) {
              m_log.error(thrdname + " " + pkse, pkse);

              parseResult.setPksError(pkse);
              m_driver.getAlarmDriver().dispatchAlarm(PAConstants.ALID_PKS_ERROR, pkse.getMessage(), 10);
            }

            m_fileInterface.checkAndCreateLocalDirs(m_destDirectory, "", m_paConfig.getSubDirectory());

            // after parsing move it into the backup or error directory
            try {
              m_fileInterface.postProcessFile(
                  m_sourceDirectory,
                  m_destDirectory,
                  "",
                  m_paConfig.getSubDirectory(),
                  fileList[i].getName(),
                  parseResult);

              m_log.info("file " + fileList[i].getName() + " successfuly moved");
            }
            catch (PostProcessFileException ppfe) {
              m_log.error(thrdname + " " + ppfe, ppfe);

              m_driver.getAlarmDriver().dispatchAlarm(PAConstants.ALID_CANT_MOVE_FILE, ppfe.getMessage(), 10);
              break;
            }
            m_log.debug(thrdname + " Sleeping " + m_paConfig.getSleepTimeBetweenEvents() + " ms");
            
            try {
              Thread.sleep(m_paConfig.getSleepTimeBetweenEvents());
            }
            catch (InterruptedException ie) {
              m_log.fatal(thrdname + " " + ie);
            }
          }
        }
        catch (FindFilesException ffe) {
          m_log.error(thrdname + " " + ffe, ffe);
          m_bConnected = false;
        }
      }
      long lTime2 = new Date().getTime();
      long lWait = (m_paConfig.getCycleTime() * 1000) - (lTime2 - lTime1);
      m_log.debug(thrdname + " Waiting " + (lWait / (60 * 1000)) + " min "
          + ((lWait / 1000) - ((lWait / 1000) / 60) * 60) + " s");
      // Wait until period is over
      if (lWait > 0) {
        try {
          Thread.sleep(lWait);
        }
        catch (InterruptedException ie) {
          m_log.fatal(thrdname + " " + ie);
        }
      }
    }
    m_log.debug(thrdname + " run() - end");
  }

  /**
   * Reads the content of the file and stores it in java objects
   * 
   * @param fileName
   *          the file name of the interface file without a path.
   */
  @Override
	public abstract void parseFile(String fileName) throws ParseFileException, PKSException;

}
