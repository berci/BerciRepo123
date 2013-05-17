package test.extend;

public class TestChild {
	
	protected int getCount() {
		return 1;
	}
	
	@Override
	public String toString() {
		return "Number: " + getCount();
	}
}
