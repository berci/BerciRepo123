package cache;

public class SimpleCacheTest2 {

	public static void main(String[] args) {
		
		SimpleCache<String> cache = new SimpleCache<String>(5);
		cache.put("1", "1");
		cache.put("2", "2");
		cache.put("3", "3");
		cache.put("4", "4");
		cache.put("5", "5");
		cache.put("6", "6");
		
		System.out.println();
		
		cache.put("7", "7");
		
		String string = cache.get("1");
		String string2 = cache.get("2");
		String string3 = cache.get("3");
		String string4 = cache.get("4");
		String string5 = cache.get("5");
		String string6 = cache.get("6");
		
		String string7 = cache.get("7");
		
		System.out.println();
		
		
	}

}
