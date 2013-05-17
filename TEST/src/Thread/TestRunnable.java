package Thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestRunnable implements Runnable {
	
	private static BufferedReader be = new BufferedReader(new InputStreamReader(System.in));
	private char c;
	private Thread th;
	private boolean stopThread = false;
	
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
			try {
				c = be.readLine().charAt(0);
				System.out.println(c);
				if(c == 'k') stop();
			}
			catch(IndexOutOfBoundsException e) {
				System.out.println("Nem karakter! Ujra!");
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
