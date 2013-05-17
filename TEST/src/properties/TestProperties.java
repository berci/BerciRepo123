package properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TestProperties {

	public static void main(String[] args) {
		Properties props = new Properties();

		try {
			File propsFile = new File("properties/dbms.properties");
			if (!propsFile.exists() || !propsFile.isFile()
					|| !propsFile.canRead()) {
			}

			FileInputStream fi = new FileInputStream(propsFile);
			props.load(fi);
			fi.close();
		} catch (IOException e) {
		}
		
		Map<String, Float> map = new HashMap<String, Float>();
		Enumeration<?> e = props.propertyNames();
		while (e.hasMoreElements()){
			String key = (String) e.nextElement();
			if(key.startsWith("VID_")){
				map.put(key, null);
			}
		}
		
		if(map.containsKey("VID_10006")){
			map.put("VID_10006", new Float(2121));
		}
		System.out.println();
	}

}
