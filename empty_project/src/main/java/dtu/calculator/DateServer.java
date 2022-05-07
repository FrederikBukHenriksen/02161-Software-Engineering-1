package dtu.calculator;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateServer {

	public GregorianCalendar calendar;

	public DateServer(int year, int month, int day) {
		this.calendar = new GregorianCalendar(year, month, day);
	}

	public GregorianCalendar getDate() {
		return calendar;
	}

	public int getYear() {
		return calendar.get(1);
	}

	// BRUGES KUN OPSTILLING AF CUCUMBER TESTS
	public void setDate(int year, int month, int date) {
		calendar = new GregorianCalendar(year, month, date);

	}
}
