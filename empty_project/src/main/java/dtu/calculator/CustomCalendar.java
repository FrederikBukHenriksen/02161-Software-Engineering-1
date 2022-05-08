package dtu.calculator;

import java.util.GregorianCalendar;

public class CustomCalendar extends GregorianCalendar {

    GregorianCalendar calendar = new GregorianCalendar();

    public CustomCalendar(int year, int week) throws Exception {
        setYear(year);
        setWeek(week);
    }

    public CustomCalendar(int year, int month, int day) {
        calendar = new GregorianCalendar(year, month - 1, day);
    }

    public CustomCalendar(int year, int month, int day, int hour, int minute) {
        calendar = new GregorianCalendar(year, month - 1, day, hour, minute);
    }

    public int getYear() {
        return calendar.get(GregorianCalendar.YEAR);
    }

    public int getWeek() {
        return calendar.get(GregorianCalendar.WEEK_OF_YEAR);
    }

    public void setYear(int year) {
        calendar.set(GregorianCalendar.YEAR, year);
    }

    public void setWeek(int week) throws Exception {
        int maxWeekNumber = calendar.getActualMaximum(GregorianCalendar.WEEK_OF_YEAR);
        if (week < 1 || week > maxWeekNumber) {
            throw new Exception("Week number not valid");
        }
        calendar.set(GregorianCalendar.WEEK_OF_YEAR, week);
    }

    public void setMonth(int month) throws Exception {
        int maxWeekNumber = calendar.getActualMaximum(GregorianCalendar.MONTH) + 1;
        if (month > maxWeekNumber) {
            throw new Exception("month not valid");
        }

    }

}