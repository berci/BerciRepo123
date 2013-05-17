@rem Run the server

start /B orbd -ORBInitialHost localhost -ORBInitialPort 3001
rem start /B tnameserv
cd C:\Users\marton\projects\eclipse-config\workspaces\conergy\equipments\CORBAServer

SET LIB=lib
SET CP=
set CP=%CP%;%LIB%\avalon-framework-4.1.5.jar
set CP=%CP%;%LIB%\idl.jar
set CP=%CP%;%LIB%\logkit-1.2.jar
set CP=%CP%;%LIB%\jacorb-2.3.0.jar
set CP=%CP%;%LIB%\test.jar
cls

start /B java -classpath %CP% com.corba.test.Server