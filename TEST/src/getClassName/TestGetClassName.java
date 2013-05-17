package getClassName;

public class TestGetClassName {
	
	public String getClassName(){
		return "TestEnum";
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		TestGetClassName t1 = new TestGetClassName();
		Class c = t1.getClassName().getClass();
		Enum obj=(Enum) c.newInstance();
		
		
	}
}
