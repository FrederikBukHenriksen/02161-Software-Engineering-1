package dtu.calculator;

import java.util.GregorianCalendar;

public abstract class Registration {

    private CustomCalendar startTime;
    private CustomCalendar endTime;

    protected ProjectPlanner projectPlanner;


    public Registration(CustomCalendar startTime, CustomCalendar endTime, ProjectPlanner projectPlanner)
            throws Exception {
        if (startTime.getDate().compareTo(endTime.getDate()) > 0) {
            throw new Exception("Start time must be before end time");
        }
        this.startTime = startTime;
        this.endTime = endTime;
        this.projectPlanner = projectPlanner;
    }

    protected void setStartTime(CustomCalendar startTime) {
        this.startTime = startTime;
    }

    protected void setEndTime(CustomCalendar endTime) {
        this.endTime = endTime;
    }

    // Get functions
    protected CustomCalendar getStartTime() {
        return startTime;
    }

    protected CustomCalendar getEndTime() {
        return endTime;
    }


}