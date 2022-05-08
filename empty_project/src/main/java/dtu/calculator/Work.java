package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Work extends Registration {

    Activity activity;


    public Work(CustomCalendar startTime, CustomCalendar endTime, ProjectPlanner projectPlanner, Activity activity) throws Exception {
        super(startTime, endTime, projectPlanner);
        if (startTime.getMinute() % 30 != 0 || endTime.getMinute() % 30 != 0) {
            throw new Exception("Only half-hour resolution allowed for workregistration");
        }
        this.activity = activity;
    }

    public CustomCalendar calendarWork(int year, int month, int dayOfMonth, int hourOfDay, int minute)
            throws Exception {

        CustomCalendar calendar = new CustomCalendar(year, month, dayOfMonth, hourOfDay, minute);
        return calendar;
    }

    public Activity getActivity() {
        return activity;
    }

}
