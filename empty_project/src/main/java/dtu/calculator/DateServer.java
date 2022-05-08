package dtu.calculator;

import java.util.GregorianCalendar;

public class DateServer {

	// DATO FASTSÆTTES P.T. ARBITRÆT, MEN BØR HENTES FRA EN SERVER.
	private GregorianCalendar calendar = new GregorianCalendar(2022, 5, 9, 23, 59);

	protected GregorianCalendar createTimestamp(int year, int month, int date, int hour, int minute) {
		return new GregorianCalendar(year, month - 1, date, hour, minute);
	}

	protected GregorianCalendar createDate(int year, int month, int date) {
		return new GregorianCalendar(year, month - 1, date);
	}

	protected GregorianCalendar createDateFromWeek(int year, int week) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(GregorianCalendar.WEEK_OF_YEAR, 18);
		cal.set(GregorianCalendar.YEAR, 2022);
		return cal;
	}

	protected GregorianCalendar getDate() {
		return calendar;
	}

	protected int getYear() {
		return calendar.get(GregorianCalendar.YEAR);
	}

	protected int getYear(GregorianCalendar gregorianCalendar) {
		return calendar.get(GregorianCalendar.YEAR);
	}

	protected int getWeek(GregorianCalendar gregorianCalendar) {
		return calendar.get(GregorianCalendar.WEEK_OF_YEAR);
	}

	// BRUGES KUN OPSTILLING AF CUCUMBER TESTS.
	protected void setDate(int year, int month, int date) {
		calendar = new GregorianCalendar(year, month, date);
	}
}
