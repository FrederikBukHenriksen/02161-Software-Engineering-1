package dtu.calculator;

import java.util.GregorianCalendar;

public class Leave extends Registration {
    String title;

    public Leave(GregorianCalendar startTime, GregorianCalendar endTime, String title) {
        super(startTime, endTime);
        this.title = title;
    }

    public void changeStartDate(GregorianCalendar startTime) {
        this.startTime = startTime;
    }

    public void changeEndDate(GregorianCalendar endTime) {
        this.endTime = endTime;
    }

    public GregorianCalendar getStartTime() {
        return startTime;
    }

    public GregorianCalendar getEndTime() {
        return endTime;
    }

    public String getLeaveTitle(){
        return title;
    }

}
