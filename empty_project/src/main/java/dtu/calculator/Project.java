package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;



public class Project {

    String title;
    String id;
    GregorianCalendar startTime;
    User projectLeader;
    private ArrayList<Activity> activities = new ArrayList<>();
    private ArrayList<User> projectEmployees = new ArrayList<>();

    public Project(String title) {
        this.title = title;
        this.id = getNextId();
    }

    private String getNextId() {
        int maxId = 0;

        for (Project project : ProjectPlanner.getProjects()) {
            int idToCompare = Integer.parseInt(project.id.split("-")[1]);
            if (idToCompare > maxId) {
                maxId = idToCompare;
            }
        }
        maxId++;

        int year = DateServer.getYear();

        return String.valueOf(year) + "-" + String.valueOf(maxId);

    }

    public void createActivity(String title) {
        if (projectLeaderLoggedIn()) {

            if (uniqueTitle(title)) {
                activities.add(new Activity(title,this));
            } else {
                // throw new Exception("Tile is already in use by another activity.");
                ErrorMessageHolder.setErrorMessage("The activity already exists");
            }
        } else {
            // throw new Exception("Only project leader can create activities.");
            ErrorMessageHolder.setErrorMessage("Project leader login is required");
        }

    }
    
    public void CucumbercreateActivity(String title) {
        activities.add(new Activity(title, this));
    }

    private boolean uniqueTitle(String title) {
        for (Activity activity : activities) {
            if (activity.getTitle().equalsIgnoreCase(title)) {
                return false;
            }
        }
        return true;
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public void setProjectLeader(User employee) {
        projectLeader = employee;
    }

    public Activity getActivity(String title) {
        for (Activity activity : activities) {
            if (activity.getTitle().equalsIgnoreCase(title)) {
                return activity;
            }
        }
        return null;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }


    public void addEmployeeToProject(String employeeID) throws Exception {
        if (projectLeaderLoggedIn()) {
            for (User employee : ProjectPlanner.getUsers()) {
                if (employeeID.equals(employee.getInitials())) {
                    if (!projectEmployees.contains(employee)) {
                        projectEmployees.add(employee);
                        return;
                    } else {
                        throw new Exception("Employee is already in project");
                    }
                }
            }
            throw new Exception("Employee with id " +employeeID+ " does not exist");
        } else {
            ErrorMessageHolder.setErrorMessage("Only a project leader can add an employee to the project");
            // throw new Exception("Only a project leader can add an employee to the project");
        }


    }

    public User getProjectleader() {
        return projectLeader;
    }

    public boolean projectLeaderLoggedIn(){
        return projectLeader == ProjectPlanner.getLoggedIn() && projectLeader != null;
    }


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    
    public ArrayList<User> getProjectEmployees() {
        return projectEmployees;

    }

}
