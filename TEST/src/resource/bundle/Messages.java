package resource.bundle;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	
	private static final String BUNDLE_NAME = "resource.bundle.messages";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	private static final ResourceBundle RESOURCE_BUNDLE_B = ResourceBundle.getBundle(BUNDLE_NAME, Locale.ENGLISH);
	
	private Messages() {
		//
	}
	
	public static String getString(String key){
		try {
			return RESOURCE_BUNDLE.getString(key) + " -------- " + RESOURCE_BUNDLE_B.getString(key);
		} catch (MissingResourceException e) {
			return "!" + key + "!";
		}
	}
	
	public static String format(String key, String idx){
		return MessageFormat.format(getString(key), new Object[]{idx});
	}
	
	public static void main(String[] args) {
		System.out.println(Messages.getString("test"));
		System.out.println(format("test", "qwqew"));
	}
}
