package com.camline;

public class TestRegExp {

	/**
	 * @param args
	 */
	
	private String test = "P2222";
	
	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public static void main(String[] args) {
		TestRegExp test = new TestRegExp();
		test.setTest("P222");
		
		if(test.getTest().matches("^[a-zA-Z]")){
			System.out.println("YES");
		}

	}

}
