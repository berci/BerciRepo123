package com.berta.thread;

public class NewThread extends Thread {
	
	public NewThread() {
		// Create a new, second thread
		super("Demo thread");
		System.out.println("Child thread: " + this);
		start();
	}
	
	// This is the entry point for the second thread
	@Override
	public void run() {
		try {
			for(int i = 5; i > 0; i--) {
				System.out.println("Child thread : " + i);
				Thread.sleep(500);
			}
		} catch (Exception e) {
			System.out.println("Child interrupted.");
		}
		System.out.println("Exiting child thread.");
	}
	
	static class ThreadDemo {
		public static void main(String[] args) {
			new NewThread();
			
			try {
				for(int i = 5; i > 0; i--) {
					System.out.println("Main thread : " + i);
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				System.out.println("Main interrupted.");
			}
			System.out.println("Exiting main thread.");
		}
	}

}
