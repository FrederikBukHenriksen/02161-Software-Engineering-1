package dtu.calculator;

import java.util.GregorianCalendar;

public abstract class Registration {

    GregorianCalendar startTime;
    GregorianCalendar endTime;

    protected Registration(GregorianCalendar startTime, GregorianCalendar endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
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