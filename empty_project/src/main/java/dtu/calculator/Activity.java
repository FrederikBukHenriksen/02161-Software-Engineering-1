package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Activity {

    // Contained
    protected Project project;

    // Containers
    private ArrayList<User> activityUsers = new ArrayList<>();

    // Class variables
    private String title;
    private int budgetedTime;
    private CustomCalendar startTime;
    private CustomCalendar endTime;
    private double activityEstimate;
    private boolean startDayIsSet;

    protected Activity(String title, Project project) {
        this.title = title;
        this.project = project;
    }

    // Create or add functions

    protected void addUserToActivity(User user) throws Exception {
        if (project.isProjectLeaderLoggedIn()) {
            if (user instanceof Administrator) {
                throw new Exception("Not allowed for administrator user");
            }
            // Check if user is already assigned to the activity
            if (!project.getProjectEmployees().contains(user)) {
                throw new Exception("User is not in the project");
            }
            if (getEmployees().contains(user)) {
                throw new Exception("User is already in the activity");
            }
            activityUsers.add(user);
        } else {
            throw new Exception("Project leader login is required");
        }
    }

    // Remove or delete functions

    protected void removeEmployeeFromActivity(User user) throws Exception {
        if (!getEmployees().contains(user)) {
            throw new Exception("User is not in the activity");
        }
        if (!project.isProjectLeaderLoggedIn()) {
            throw new Exception("Project leader login is required");
        }
        activityUsers.remove(user);
    }

    // Check and help functions

    // Set functions

    protected void setTitle(String title) {
        this.title = title;
    }

    protected void setStartDate(int year, int week) throws Exception {
        if (!project.isProjectLeaderLoggedIn()) {
            throw new Exception("Project leader login is required");
        }
        startDayIsSet = true;
        startTime = new CustomCalendar(year, week);
    }

    protected void setActivityEstimate(double time) {
        if(getEmployees().contains(project.projectPlanner.getLoggedIn())) {
            activityEstimate = time;
        } else {
            throw new IllegalArgumentException("The user is not assigned this Activity");
        }
    }

    public void setBudgetedTime(int budgetedTime) {
        this.budgetedTime = budgetedTime;
    }

    protected void setEndDate(int year, int week) throws Exception {
        if (!project.isProjectLeaderLoggedIn()) {
            throw new Exception("Project leader login is required");
        }
        if(startDayIsSet){
            CustomCalendar temp = new CustomCalendar(year, week);
            if(startTime.getDate().compareTo(temp.getDate()) > 0){
                throw new Exception("End date cannot be before start date");
            }
        }
        endTime = new CustomCalendar(year, week);
        
    }

    // Get functions

    protected String getTitle() {
        return title;
    }

    protected CustomCalendar getStartDate() {
        return startTime;
    }

    protected CustomCalendar getEndDate() {
        return endTime;
    }

    protected int getWeek(GregorianCalendar gregorianCalendar) {
        return gregorianCalendar.get(gregorianCalendar.WEEK_OF_YEAR);
    }

    protected ArrayList<User> getEmployees() {
        return activityUsers;
    }

    protected double getActivityEstimate() {
        return activityEstimate;
    }

    protected int getBudgetedTime() {
        return budgetedTime;
    }

}