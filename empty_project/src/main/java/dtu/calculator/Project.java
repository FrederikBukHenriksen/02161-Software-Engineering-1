package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Project {

    // Contained
    ProjectPlanner projectPlanner;
    DateServer dateServer;

    // Containers
    private ArrayList<Activity> projectActivities = new ArrayList<>();
    private ArrayList<User> projectUsers = new ArrayList<>();

    // Project variables
    String title;
    String id;
    GregorianCalendar startDate;
    User projectLeader;

    public Project(String title, ProjectPlanner projectPlanner) {
        this.title = title;
        this.projectPlanner = projectPlanner;
        id = genNextId();
    }

    // Create or add functions

    public void createActivity(String title) throws Exception {
        if (isProjectLeaderLoggedIn()) {
            if (isActivityTitleUnique(title)) {
                projectActivities.add(new Activity(title, this));
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
                throw new Exception("User is already in the project");
            }
        } else {
            throw new Exception("Project leader login is required");
        }

    }

    // Remove or delete functions

    public void removeUserFromProject(User user) throws Exception {
        if (isProjectLeaderLoggedIn()) {
            if (!projectUsers.remove(user)) // Returns boolean wether remove was possible
                throw new Exception("employee isn't in the project");
        } else {
            throw new Exception("Project leader login is required");
        }
    }

    public void removeActivity(Activity activity) throws Exception {
        if (isProjectLeaderLoggedIn()) {
            projectActivities.remove(activity);
        } else {
            throw new Exception("Project leader login is required");
        }
    }

    // Check and help functions

    private String genNextId() {
        // Find highest id in use, and create the following.
        int maxIdInUse = 0;
        for (Project project : projectPlanner.getProjects()) {
            int idToCompare = Integer.parseInt(project.getId().split("-")[1]);
            if (idToCompare > maxIdInUse) {
                maxIdInUse = idToCompare;
            }
        }
        maxIdInUse++;

        return String.valueOf(projectPlanner.dateServer.getYear()) + "-" + String.valueOf(maxIdInUse);

    }

    private boolean isActivityTitleUnique(String title) {
        for (Activity activity : projectActivities) {
            if (activity.getTitle().equalsIgnoreCase(title)) {
                return false;
            }
        }
        return true;
    }

    public boolean isProjectLeaderLoggedIn() {
        return projectLeader == projectPlanner.getLoggedIn() && projectLeader != null;
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
        if (projectPlanner.administratorLoggedIn()) {
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
        return projectActivities;
    }

    public Activity getActivity(String title) throws Exception {
        for (Activity activity : getActivities()) {
            if (activity.getTitle().equalsIgnoreCase(title)) {
                return activity;
            }
        }
        throw new Exception("Activity does not exist in the project");
    }

    // JUNIT Helpfunctions

    public void cucumberAddUserToProject(User user) throws Exception {
        projectUsers.add(user);
    }

    public void cucumberSetProjectLeader(User user) {
        projectLeader = user;
    }

    public void CucumberCreateActivity(String title) {
        projectActivities.add(new Activity(title, this));
    }
}
