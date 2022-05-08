package dtu.calculator;

import java.util.GregorianCalendar;

public class Leave extends Registration {
    String title;

    public Leave(CustomCalendar startTime, CustomCalendar endTime, ProjectPlanner projectPlanner, String title) {
        super(startTime, endTime, projectPlanner);
        this.title = title;
    }

    
    public void changeDates(CustomCalendar startTime, CustomCalendar endTime) throws Exception {
        if(startTime.compareTo(endTime) > 0) {
            throw new Exception("Start time must be before end time");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public String getLeaveTitle(){
        return title;
    }

}
