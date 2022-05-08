package dtu.calculator;

import java.util.GregorianCalendar;

public class CustomCalendar extends GregorianCalendar {

    GregorianCalendar calendar = new GregorianCalendar();

    protected CustomCalendar(int year, int week) throws Exception {
        setTimeZero();
        setYear(year);
        setWeek(week);
    }

    protected CustomCalendar(int year, int month, int date) {
        setTimeZero();
        setYear(year);
        setMonth(month);
        setDate(date);
        setTimeZero();
    }

    protected CustomCalendar(int year, int month, int date, int hour, int minute) {
        setTimeZero();
        setYear(year);
        setMonth(month);
        setDate(date);
        setHour(hour);
        setMinute(minute);
    }

    // Set functions
    protected void setYear(int year) {
        calendar.set(GregorianCalendar.YEAR, year);
    }

    protected void setWeek(int week) throws Exception {
        int maxWeekNumber = calendar.getActualMaximum(GregorianCalendar.WEEK_OF_YEAR);
        if (week < 1 || week > maxWeekNumber) {
            throw new Exception("Week number not valid");
        }
        calendar.set(GregorianCalendar.WEEK_OF_YEAR, week);
    }

    protected void setMonth(int month) {
        calendar.set(GregorianCalendar.MONTH, month + 1);
    }

    protected void setDate(int date) {
        calendar.set(GregorianCalendar.DAY_OF_MONTH, date);
    }

    protected void setHour(int hour) {
        calendar.set(GregorianCalendar.HOUR_OF_DAY, hour);
    }

    protected void setMinute(int minute) {
        calendar.set(GregorianCalendar.MINUTE, minute);
    }

    protected void setTimeZero() {
        calendar.set(calendar.HOUR_OF_DAY, 0);
        calendar.set(calendar.MINUTE, 0);
        calendar.set(calendar.SECOND, 0);
        calendar.set(calendar.MILLISECOND, 0);
    }

    // Get functions
    protected GregorianCalendar getDate() {
        return calendar;
    }

    protected int getYear() {
        return calendar.get(GregorianCalendar.YEAR);
    }

    protected int getWeek() {
        return calendar.get(GregorianCalendar.WEEK_OF_YEAR);
    }

    protected int getMinute() {
        return calendar.get(GregorianCalendar.MINUTE);

    }

}