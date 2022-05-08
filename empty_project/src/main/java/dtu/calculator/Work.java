package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Work extends Registration {

    Activity activity;

    public Work(GregorianCalendar startTime, GregorianCalendar endTime, ProjectPlanner projectPlanner, Activity activity) {
        super(startTime, endTime, projectPlanner);
        this.activity = activity;
    }

    public GregorianCalendar calendarWork(int year, int month, int dayOfMonth, int hourOfDay, int minute)
            throws Exception {
        if (minute % 30 != 0) {
            throw new Exception("Only half-hour resolution allowed for work registration");
        }
        GregorianCalendar calendar = projectPlanner.dateServer.createTimestamp(year, month, dayOfMonth, hourOfDay, minute);
        return calendar;
    }

    public Activity getActivity() {
        return activity;
    }

}
