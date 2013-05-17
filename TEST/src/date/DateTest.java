package date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Calendar cal = new GregorianCalendar(1970, 01, 01);
		Date creationDate = cal.getTime();
		System.out.println(creationDate);

	}

}
