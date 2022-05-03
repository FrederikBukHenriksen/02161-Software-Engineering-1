package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Project {

    // Containers
    private ArrayList<Activity> activities = new ArrayList<>();
    private ArrayList<User> projectEmployees = new ArrayList<>();

    // Project variables
    String title;
    String id;
    GregorianCalendar startDate;
    User projectLeader;


    public Project(String title) {
        this.title = title;
        id = getNextId();
    }

    // Create or add functions

    public void createActivity(String title) throws Exception {
        if (isProjectLeaderLoggedIn()) {
            if (isActivityTitleUnique(title)) {
                activities.add(new Activity(title, this));
            } else {
                throw new Exception("The activity already exists");
            }
        } else {
            throw new Exception("Project leader login is required");
        }
    }

    public void addEmployeeToProject(User employee) throws Exception {
        if (isProjectLeaderLoggedIn()) {
            if (!projectEmployees.contains(employee)) {
                projectEmployees.add(employee);
            } else {
                throw new Exception("Employee is already in project");
            }
        } else {
            throw new Exception("Only a project leader can add an employee to the project");
        }

    }

    // Remove or delete functions

    public void removeEmployeeFromProject(User employee) throws Exception {
        if (isProjectLeaderLoggedIn()) {
            if (!projectEmployees.remove(employee)) // Returns boolean wether remove was possible
                throw new Exception("employee isn't in the project");

            // ErrorMessageHolder.setErrorMessage("employee isn't in the project");
        } else {
            throw new Exception("Only a project leader can remove an employee to the project");

            // ErrorMessageHolder.setErrorMessage("Only a project leader can remove an
            // employee to the project");
        }
    }

    public void removeActivity(Activity activity) throws Exception {
        if (isProjectLeaderLoggedIn()) {
            activities.remove(activity);
        } else {
            throw new Exception("Project leader login is required");
        }
    }

    // Check and help functions

    private String getNextId() {
        // Find highest id in use, and create the following.
        int maxIdInUse = 0;
        for (Project project : ProjectPlanner.getProjects()) {
            int idToCompare = Integer.parseInt(project.getId().split("-")[1]);
            if (idToCompare > maxIdInUse) {
                maxIdInUse = idToCompare;
            }
        }
        maxIdInUse++;

        return String.valueOf(DateServer.getYear()) + "-" + String.valueOf(maxIdInUse);

    }

    private boolean isActivityTitleUnique(String title) {
        for (Activity activity : activities) {
            if (activity.getTitle().equalsIgnoreCase(title)) {
                return false;
            }
        }
        return true;
    }

    public boolean isProjectLeaderLoggedIn() {
        return projectLeader == ProjectPlanner.getLoggedIn() && projectLeader != null;
    }

    // Set functions

    public void setStartDate(int day, int month, int year) throws Exception {
        if (isProjectLeaderLoggedIn()) {
            startDate = new GregorianCalendar(year, month, day);
        } else {
            throw new Exception("Project leader login is required");
        }
    }

    public void setProjectLeader(User employee) throws Exception {
        if (ProjectPlanner.administratorLoggedIn()) {
            projectLeader = employee;
        } else {
            throw new Exception("Administrator login is required");
        }
    }

    // Get functions

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public GregorianCalendar getStartDate() {
        return startDate;
    }

    public ArrayList<User> getProjectEmployees() {
        return projectEmployees;
    }

    public User getProjectleader() {
        return projectLeader;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public Activity getActivity(String title) {
        for (Activity activity : activities) {
            if (activity.getTitle().equalsIgnoreCase(title)) {
                return activity;
            }
        }
        return null;
    }

    // JUNIT Helpfunctions

    public void cucumberAddEmployeeToProject(String employeeID) throws Exception {
        projectEmployees.add(ProjectPlanner.getUser(employeeID));
    }

    public void cucumberSetProjectLeader(Employee employee) {
        projectLeader = employee;
    }

    public void CucumbercreateActivity(String title) {
        activities.add(new Activity(title, this));
    }
}
