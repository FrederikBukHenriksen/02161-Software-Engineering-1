package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Project {

    String title;
    String id;
    GregorianCalendar startTime;
    User projectLeader;
    ArrayList<Activity> activities = new ArrayList<>();
    ArrayList<User> projectEmployees = new ArrayList<>();

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
        if (uniqueTitle(title)) {
            activities.add(new Activity(title));
        } else {
            // throw new Exception("Tile is already in use by another activity.");
        }
    }

    private boolean uniqueTitle(String title) {
        for (Activity activity : activities) {
            if (activity.title.equalsIgnoreCase(title)) {
                return false;
            }
        }
        return true;
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public void setProjectLeader(Employee employee) {
        projectLeader = employee;
    }

    public void addEmployeeToProject(String employeeID) throws Exception {
        if (projectLeaderLoggedIn()) {
            for (User employee : ProjectPlanner.getUsers()) {
                if (employeeID == employee.getInitials()) {
                    if (!projectEmployees.contains(employee)) {
                        projectEmployees.add(employee);
                        return;
                    } else {
                        throw new Exception("Employee is already in project");
                    }
                }
            }
            throw new Exception("Employee with id " +employeeID+ " does not exist");
        }


    }

    public User getProjectleader() {
        return projectLeader;
    }

    public boolean projectLeaderLoggedIn(){
        return projectLeader == ProjectPlanner.getLoggedIn();
    }


    public String getId() {
        return id;
    }


}
