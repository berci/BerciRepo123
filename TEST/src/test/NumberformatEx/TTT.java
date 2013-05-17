package test.NumberformatEx;

public class TTT {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TTT ttt = new TTT();
		ttt.test();
		System.out.println("tttt");

	}

	private void test() {
		try {
			int i = Integer.parseInt("222aa");
		} catch (Exception e) {
			System.out.println("not OK");
		}
	}

	
	
}
