package Thread;

public class TestRunnable2 implements Runnable{
	private Thread th;
	private boolean stopThread = false;
	private int i = 0; 
	
	public boolean isStopThread() {
		return stopThread;
	}

	public void setStopThread(boolean stopThread) {
		this.stopThread = stopThread;
	}

	public void start(){
		th = new Thread(this);
		th.start();
	}

	public void stop(){
		stopThread = true; 
		if(th.isAlive()){
			th.interrupt();
		}
	}
	
	@Override
	public void run() {
		while(!stopThread){
			System.out.println(i);
			i++;
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(i == 10) stop();
		}
	}
}