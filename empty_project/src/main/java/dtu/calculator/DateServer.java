package dtu.calculator;

import java.util.GregorianCalendar;

public class DateServer {

	// DATO FASTSÆTTES P.T. ARBITRÆT, MEN BØR HENTES FRA EN SERVER.
	private CustomCalendar calendar = new CustomCalendar(2022, 5, 9, 23, 59);

	// protected CustomCalendar createTimestamp(int year, int month, int date, int hour, int minute) {
	// 	return new CustomCalendar(year, month - 1, date, hour, minute);
	// }

	// protected CustomCalendar createDate(int year, int month, int date) {
	// 	return new CustomCalendar(year, month - 1, date);
	// }

	// protected CustomCalendar createDateFromWeek(int year, int week) {
	// 	CustomCalendar cal = new CustomCalendar();
	// 	cal.set(CustomCalendar.WEEK_OF_YEAR, 18);
	// 	cal.set(CustomCalendar.YEAR, 2022);
	// 	return cal;
	// }

	protected CustomCalendar getDate() {
		return calendar;
	}

	// protected int getYear() {
	// 	return calendar.get(CustomCalendar.YEAR);
	// }

	// protected int getYear(CustomCalendar gregorianCalendar) {
	// 	return calendar.get(CustomCalendar.YEAR);
	// }

	// protected int getWeek(CustomCalendar gregorianCalendar) {
	// 	return calendar.get(CustomCalendar.WEEK_OF_YEAR);
	// }

	// BRUGES KUN OPSTILLING AF CUCUMBER TESTS.
	protected void setDate(int year, int month, int date) {
		calendar = new CustomCalendar(year, month, date);
	}
}
