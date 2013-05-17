package test.extend;

public class TestM extends TestChild{
	
	@Override
	protected int getCount() {
		return 2;
	}
	
	public String[] getStringArray(){
		return new String[]{};
	}

	public static void main(String[] args) {
//		TestM t1 = new TestM();
//		TestChild tC1 = new TestChild();
//		System.out.println(tC1.toString());
//		System.out.println(t1.toString());
//		
//		
//		String[] array = t1.getStringArray();
//		System.out.println(array.length);
		
		String[] array = null;
		try {
			array = new String[]{};
			System.out.println(array[0]);
		} catch (Exception e) {
			System.out.println("sdsdsd");
		}
		System.out.println(array[0]);
		System.out.println("sasasas");
	}

}
