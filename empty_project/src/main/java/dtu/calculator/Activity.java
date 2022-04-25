package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Activity {

    private String title;
    int budgetedTime;
    GregorianCalendar startTime;
    GregorianCalendar endTime;
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
        activityEmployees.add(employee);
    }

    public void removeEmployee(User employee) {
        activityEmployees.remove(employee);
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<User> getEmployees() {
        return activityEmployees;
    }

    public void setActivityEstimate(double time) {
        activityEstimate = time;
    }

    public double getActivityEstimate(){
        return activityEstimate;
    }



}