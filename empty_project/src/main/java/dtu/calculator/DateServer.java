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

	protected GregorianCalendar createTimestamp(int year, int month, int date, int hour, int minute) {
		return new GregorianCalendar(year, month - 1, date, hour, minute);
	}

	protected GregorianCalendar createDate(int year, int month, int date) {
		return new GregorianCalendar(year, month-1, date);
	}

	// BRUGES KUN OPSTILLING AF CUCUMBER TESTS
	public void setDate(int year, int month, int date) {
		calendar = new GregorianCalendar(year, month, date);

	}
}
