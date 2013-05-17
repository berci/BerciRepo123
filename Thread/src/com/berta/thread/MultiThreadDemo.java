package com.berta.thread;

public class MultiThreadDemo {
	public static void main(String[] args) {
		new NewRunnable("One");
		new NewRunnable("Two");
		new NewRunnable("Three");

		try {
			// wait for other threads to end
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.out.println("Main thread Interrupted");
		}
		System.out.println("Main thread exiting.");
	}
}
