package com.berta.thread;

public class NewRunnable implements Runnable {
	
	Thread t;
	String name;
	
	public NewRunnable(String name) {
		// Create a new, second thread
		this.name = name;
		t = new Thread(this, name);
		System.out.println("Child thread: " + t);
		t.start();
	}
	
	// This is the entry point for the second thread
	@Override
	public void run() {
		try {
			for(int i = 5; i > 0; i--) {
				System.out.println(name + ": " + i);
				Thread.sleep(500);
			}
		} catch (Exception e) {
			System.out.println(name + " interrupted.");
		}
		System.out.println(name + " exiting.");
	}
	
	static class ThreadDemo {
		public static void main(String[] args) {
			new NewRunnable("Demo thread");
			
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
