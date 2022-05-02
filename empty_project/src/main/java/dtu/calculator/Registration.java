package dtu.calculator;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Registration
 */

public class Registration {

    GregorianCalendar startTime;
    GregorianCalendar endTime;

    public Registration(GregorianCalendar startTime, GregorianCalendar endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getHourDifference(GregorianCalendar startTime, GregorianCalendar endTime) {
        return endTime.getTimeInMillis() - startTime.getTimeInMillis();
    }

}