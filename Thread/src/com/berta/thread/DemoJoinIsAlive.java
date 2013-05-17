package com.berta.thread;

public class DemoJoinIsAlive {
	public static void main(String[] args) {
		NewRunnable t1 = new NewRunnable("One");
		NewRunnable t2 = new NewRunnable("Two");
		NewRunnable t3 = new NewRunnable("Three");

		System.out.println("Thread One is alive " + t1.t.isAlive());
		System.out.println("Thread Two is alive " + t2.t.isAlive());
		System.out.println("Thread Three is alive " + t3.t.isAlive());

		// Wait for threads to finish
		try {
			System.out.println("Waiting for threads to finish.");
			t1.t.join();
			t2.t.join();
			t3.t.join();
		} catch (InterruptedException e) {
			System.out.println("Main thread Interrupted");
		}
		
		System.out.println("Thread One is alive " + t1.t.isAlive());
		System.out.println("Thread Two is alive " + t2.t.isAlive());
		System.out.println("Thread Three is alive " + t3.t.isAlive());

		System.out.println("Main thread exiting.");
	}
}
