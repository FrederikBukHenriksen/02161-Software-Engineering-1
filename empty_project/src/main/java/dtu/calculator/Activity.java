package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Activity {

    private String title;
    int budgetedTime;
    String startTime;
    String endTime;
    double activityEstimate;
    private Project project;
    ArrayList<User> activityEmployees = new ArrayList<>();

    public Activity(String title, Project project) {
        this.title = title;
        this.project = project;
    }

    public void addEmployeeToActivity(User employee) {
        boolean found = false;
        for (User user : activityEmployees) {
            if (user.getInitials().equalsIgnoreCase(employee.getInitials())) {
                found = true;
                user.activities.add(this);
                ErrorMessageHolder.setErrorMessage("The employee is already assigned to the activity");
            }
        }
        if (project.projectLeaderLoggedIn()) {
            if (found == false) {
                activityEmployees.add(employee);
            }
        } else {
            ErrorMessageHolder.setErrorMessage("Project leader login is required");
        }
    }

    public void cucumberAddEmployeeToActivity(User employee) {
        employee.activities.add(this);
        activityEmployees.add(employee);
    }

    public void removeEmployee(User employee) {
        activityEmployees.remove(employee);
    }

    public String getTitle() {
        return title;
    }

    public void setStartDate(Integer Year, Integer Week) {
        if (project.projectLeaderLoggedIn()) {
            startTime = Year + "-" + Week;
        } else {
            ErrorMessageHolder.setErrorMessage("Project leader login is required");
        }

    }

    public void setEndDate(Integer Year, Integer Week) { // TODO: Mangler code coverage.
        if (project.projectLeaderLoggedIn()) {
            endTime = Year + "-" + Week;
        } else {
            ErrorMessageHolder.setErrorMessage("Project leader login is required");
        }
    }

    // public void setEndDate(Integer Year, Integer Week) {
    // if(Week <54){
    // if (project.projectLeaderLoggedIn()) {
    // if(Integer.valueOf(startTime.substring(0, 4))<=Year &&
    // Integer.valueOf(startTime.substring(startTime.length()-2,
    // startTime.length()))<=Week){
    // endTime = Year + "-" + Week;
    // } else{
    // ErrorMessageHolder.setErrorMessage("The end date is before the start date");
    // }
    // } else {
    // ErrorMessageHolder.setErrorMessage("Project leader login is required");
    // }
    // } else{
    // ErrorMessageHolder.setErrorMessage("Week number is out of range");
    // }
    // }

    public String getStartDate() {
        return startTime;

    }

    public String getEndDate() {
        return endTime;
    }

    public ArrayList<User> getEmployees() {
        return activityEmployees;
    }

    public void setActivityEstimate(double time) {
        activityEstimate = time;
    }

    public double getActivityEstimate() {
        return activityEstimate;
    }

}