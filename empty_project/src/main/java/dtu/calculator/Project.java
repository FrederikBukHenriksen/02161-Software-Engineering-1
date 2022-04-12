package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Project {

    String title;
    int id;
    GregorianCalendar startTime;
    User projectLeader;
    ArrayList<Activity> activities = new ArrayList<>();
    ArrayList<User> projectEmployees = new ArrayList<>();

    public Project(String title) {
        this.title = title;
        this.id = getNextId();
    }

    private int getNextId() {
        // // int year = DateServer.getYear();
        // idIncrementer++;
        // String s = Integer.toString(idIncrementer);
        // String blanks = "";
        // if (s.length() == 1) {
        // blanks = "00";
        // }
        // if(s.length()== 2){
        // blanks = "0";
        // }
        // return Integer.valueOf( "22" +blanks+ idIncrementer);
        return 22001;
        // TODO: "Implement this method";
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


    public int getId(){
        return id;
    }


}
