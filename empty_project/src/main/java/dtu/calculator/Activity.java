package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Activity {

    // Contained
    Project project;

    // Containers
    ArrayList<User> activityEmployees = new ArrayList<>();

    // Class variables
    private String title;
    int budgetedTime;
    String startTime;
    String endTime;
    double activityEstimate;

    public Activity(String title, Project project) {
        this.title = title;
        this.project = project;
    }

    // Create or add functions

    public void addUserToActivity(User user) throws Exception {
        if (project.isProjectLeaderLoggedIn()) {
            // Check if user is already assigned to the activity
            if (!project.getProjectEmployees().contains(user)) {
                throw new Exception("User is not in the project");
            }
            if (getEmployees().contains(user)) {
                throw new Exception("User is already in the activity");
            }
            activityEmployees.add(user);
        } else {
            throw new Exception("Project leader login is required");
        }
    }

    // Remove or delete functions

    public void removeEmployee(User user) throws Exception {
        if (!getEmployees().contains(user)) {
            throw new Exception("User is not in the activity");
        }
        activityEmployees.remove(user);
    }

    // Check and help functions

    // Set functions

    public void setStartDate(Integer Year, Integer Week) {
        if (project.isProjectLeaderLoggedIn()) {
            startTime = Year + "-" + Week;
        } else {
            ErrorMessageHolder.setErrorMessage("Project leader login is required");
        }

    }

    public void setActivityEstimate(double time) {
        activityEstimate = time;
    }

    public void setEndDate(Integer Year, Integer Week) { // TODO: Mangler code coverage.  
        if (project.isProjectLeaderLoggedIn()) {
            endTime = Year + "-" + Week;
        } else {
            ErrorMessageHolder.setErrorMessage("Project leader login is required");
        }
    }

    // Get functions

    public String getTitle() {
        return title;
    }

    public String getStartDate() {
        return startTime;
    }

    public ArrayList<User> getEmployees() {
        return activityEmployees;
    }

    public double getActivityEstimate() {
        return activityEstimate;
    }

    // JUNIT Helpfunctions
    public void cucumberAddEmployeeToActivity(User employee) {
        activityEmployees.add(employee);
    }
}