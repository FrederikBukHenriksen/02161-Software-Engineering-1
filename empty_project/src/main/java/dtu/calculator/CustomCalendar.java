package dtu.calculator;

import java.time.Year;
import java.util.GregorianCalendar;

public class CustomCalendar extends GregorianCalendar {

    GregorianCalendar calendar = new GregorianCalendar();

    protected CustomCalendar(int year, int week) throws Exception {
        setTimeZero();
        setYear(year);
        setWeek(week);
    }

    protected CustomCalendar(int year, int month, int date) throws Exception {
        setTimeZero();
        setYear(year);
        setMonth(month);
        setDate(date);
        setTimeZero();
    }

    protected CustomCalendar(int year, int month, int date, int hour, int minute) throws Exception {
        setTimeZero();
        setYear(year);
        setMonth(month);
        setDate(date);
        setHour(hour);
        setMinute(minute);
    }

    protected CustomCalendar() {
        
    }

    // Set functions
    protected void setYear(int year) {
        calendar.set(GregorianCalendar.YEAR, year);
    }

    protected void setWeek(int week) throws Exception {
        int max = calendar.getActualMaximum(GregorianCalendar.WEEK_OF_YEAR);
        if (week < 1 || week > max) {
            throw new Exception("Week number not valid");
        }
        calendar.set(GregorianCalendar.WEEK_OF_YEAR, week);
    }

    protected void setMonth(int month) throws Exception {
        int max = calendar.getActualMaximum(GregorianCalendar.MONTH);
        if ( month< 1 || month > max) {
            throw new Exception("Input not valid");
        }
        calendar.set(GregorianCalendar.MONTH, month + 1);
    }

    protected void setDate(int date) throws Exception {
        int max = calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        if ( date< 1 || date > max) {
            throw new Exception("Input not valid");
        }
        calendar.set(GregorianCalendar.DAY_OF_MONTH, date);
    }

    protected void setHour(int hour) throws Exception {
        int max = calendar.getActualMaximum(GregorianCalendar.HOUR_OF_DAY);
        if ( hour< 0 || hour > max) {
            throw new Exception("Input not valid");
        }
        calendar.set(GregorianCalendar.HOUR_OF_DAY, hour);
    }

    protected void setMinute(int minute) throws Exception {
        int max = calendar.getActualMaximum(GregorianCalendar.MINUTE);
        if ( minute < 0 || minute >= max) {
            throw new Exception("Input not valid");
        }
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

    protected String getDateString() {
        return "year: " + getYear() + " WEEK: " + getWeek();
    }
    
    protected String getDateStringFull() {
        return "year:" + getYear() + " Month: " + getMonth() + " Day: " + getDay();
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
    protected int getMonth() {
        return calendar.get(GregorianCalendar.MONTH);
    }
    protected int getDay() {
        return calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }


}