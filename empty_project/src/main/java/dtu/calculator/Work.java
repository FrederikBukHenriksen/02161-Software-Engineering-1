package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Work extends Registration {

    Activity activity;

    public Work(GregorianCalendar startTime, GregorianCalendar endTime, Activity activity) {
        super(startTime, endTime);
        this.activity = activity;
    }

    public static GregorianCalendar calendarWork(int year, int month, int dayOfMonth, int hourOfDay, int minute)
            throws Exception {
        if (minute % 30 != 0) {
            throw new Exception("Only half-hour resolution allowed for work registration");
        }
        GregorianCalendar calendar = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute);
        return calendar;
    }

    public Activity getActivity() {
        return activity;
    }

}
