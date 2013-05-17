package Thread;

import java.io.IOException;

public class TestRunner {

	public static void main(String[] args) throws IOException {
		//TestRunnable tr = new TestRunnable();
		
		TestRunnable2 tr = new TestRunnable2();
		tr.start();
		
		
	}
}
