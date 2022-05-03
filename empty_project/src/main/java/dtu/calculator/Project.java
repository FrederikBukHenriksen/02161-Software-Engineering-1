package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Project {

    // Containers
    private ArrayList<Activity> projectActovoties = new ArrayList<>();
    private ArrayList<User> projectUsers = new ArrayList<>();

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
                projectActovoties.add(new Activity(title, this));
            } else {
                throw new Exception("The activity already exists");
            }
        } else {
            throw new Exception("Project leader login is required");
        }
    }

    public void addUserToProject(User user) throws Exception {
        if (isProjectLeaderLoggedIn()) {
            if (!projectUsers.contains(user)) {
                projectUsers.add(user);
            } else {
                throw new Exception("Employee is already in project");
            }
        } else {
            throw new Exception("Only a project leader can add an employee to the project");
        }

    }

    // Remove or delete functions

    public void removeUserFromProject(User user) throws Exception {
        if (isProjectLeaderLoggedIn()) {
            if (!projectUsers.remove(user)) // Returns boolean wether remove was possible
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
            projectActovoties.remove(activity);
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
        for (Activity activity : projectActovoties) {
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

    public void setProjectLeader(User user) throws Exception {
        if (ProjectPlanner.administratorLoggedIn()) {
            projectLeader = user;
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
        return projectUsers;
    }

    public User getProjectleader() {
        return projectLeader;
    }

    public ArrayList<Activity> getActivities() {
        return projectActovoties;
    }

    public Activity getActivity(String title) {
        for (Activity activity : projectActovoties) {
            if (activity.getTitle().equalsIgnoreCase(title)) {
                return activity;
            }
        }
        return null;
    }

    // JUNIT Helpfunctions

    public void cucumberAddUserToProject(String userID) throws Exception {
        projectUsers.add(ProjectPlanner.getUser(userID));
    }

    public void cucumberSetProjectLeader(User user) {
        projectLeader = user;
    }

    public void CucumbercreateActivity(String title) {
        projectActovoties.add(new Activity(title, this));
    }
}
