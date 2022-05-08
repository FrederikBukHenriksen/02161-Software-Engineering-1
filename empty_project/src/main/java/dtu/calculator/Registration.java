package dtu.calculator;

import java.util.GregorianCalendar;

public abstract class Registration {

    GregorianCalendar startTime;
    GregorianCalendar endTime;

<<<<<<< Updated upstream
    protected Registration(GregorianCalendar startTime, GregorianCalendar endTime) {
=======
    protected ProjectPlanner projectPlanner;

    public Registration(GregorianCalendar startTime, GregorianCalendar endTime, ProjectPlanner projectPlanner) {
>>>>>>> Stashed changes
        this.startTime = startTime;
        this.endTime = endTime;
        this.projectPlanner = projectPlanner;
    }

    // Set functions
    public void setEndTime(GregorianCalendar endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(GregorianCalendar startTime) {
        this.startTime = startTime;
    }

    // Get functions
    protected GregorianCalendar getStartTime() {
        return startTime;
    }

    protected GregorianCalendar getEndTime() {
        return endTime;
    }


}