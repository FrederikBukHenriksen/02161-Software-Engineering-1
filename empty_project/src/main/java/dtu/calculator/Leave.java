package dtu.calculator;

import java.util.GregorianCalendar;

public class Leave extends Registration {
    String title;

    public Leave(GregorianCalendar startTime, GregorianCalendar endTime, ProjectPlanner projectPlanner, String title) {
        super(startTime, endTime, projectPlanner);
        this.title = title;
    }

    
    public void changeDates(GregorianCalendar startTime, GregorianCalendar endTime) {
        if(startTime.compareTo(endTime) > 0) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public String getLeaveTitle(){
        return title;
    }

}
