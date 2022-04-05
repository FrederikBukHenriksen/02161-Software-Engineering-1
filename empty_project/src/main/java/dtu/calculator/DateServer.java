package dtu.calculator;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateServer {

	public static GregorianCalendar calendar;

	public static GregorianCalendar getDate() {
		return calendar;
	}

	public static int getYear() {
		return calendar.get(1);
	}

	// BRUGES KUN TIL CUCUMBER TESTS
	public static void setDate(int year, int month, int date) {
		calendar = new GregorianCalendar(year, month, date);

	}
}
