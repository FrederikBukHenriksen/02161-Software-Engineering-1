package dtu.calculator;

public class Leave extends Registration {
    String title;
    User assignedUser;


    public Leave(CustomCalendar startTime, CustomCalendar endTime, ProjectPlanner projectPlanner, String title , User employee) throws Exception {
        super(startTime, endTime, projectPlanner);
        this.title = title;
        this.assignedUser = employee;
    }

    
    public void changeDates(CustomCalendar startTime, CustomCalendar endTime) throws Exception {
        if (startTime.getDate().compareTo(endTime.getDate()) > 0) {
            throw new Exception("Start time must be before end time");
        }
        setStartTime(startTime);
        setEndTime(endTime);
    }
    


    public String getLeaveTitle() {
        return title;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public String getAssignedUserInitial() {
        return assignedUser.getInitials();
    }

    
}
