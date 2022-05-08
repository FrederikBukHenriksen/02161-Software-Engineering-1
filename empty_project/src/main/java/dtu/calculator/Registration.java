package dtu.calculator;

import java.util.GregorianCalendar;

public abstract class Registration {

    CustomCalendar startTime;
    CustomCalendar endTime;

    protected ProjectPlanner projectPlanner;

    public Registration(CustomCalendar startTime, CustomCalendar endTime, ProjectPlanner projectPlanner) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.projectPlanner = projectPlanner;
    }



    // Get functions
    protected CustomCalendar getStartTime() {
        return startTime;
    }

    protected CustomCalendar getEndTime() {
        return endTime;
    }


}