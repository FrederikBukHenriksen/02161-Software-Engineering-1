package dtu.calculator;

import java.util.GregorianCalendar;

public abstract class Registration {

    CustomCalendar startTime;
    CustomCalendar endTime;

    protected ProjectPlanner projectPlanner;

    public Registration(CustomCalendar startTime, CustomCalendar endTime, ProjectPlanner projectPlanner) throws Exception {
        if(startTime.compareTo(endTime) > 0) {
            throw new Exception("Start time must be before end time");
        }
        this.startTime = startTime;
        this.endTime = endTime;
        this.projectPlanner = projectPlanner;
    }

    // Set functions
    public void setEndTime(CustomCalendar endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(CustomCalendar startTime) {
        this.startTime = startTime;
    }

    // Get functions
    protected CustomCalendar getStartTime() {
        return startTime;
    }

    protected CustomCalendar getEndTime() {
        return endTime;
    }


}