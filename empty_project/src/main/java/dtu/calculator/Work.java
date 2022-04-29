package dtu.calculator;

import java.util.GregorianCalendar;

public class Work extends Registration {

    Activity activity;

    public Work(GregorianCalendar startTime, GregorianCalendar endTime, Activity activity) {
        super(startTime, endTime);
        this.activity = activity;
    }

}
