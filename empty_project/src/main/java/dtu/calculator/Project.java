package dtu.calculator;

import java.util.ArrayList;

public class Project {

    // Contained
    protected ProjectPlanner projectPlanner;
    protected DateServer dateServer;

    // Containers
    private ArrayList<Activity> projectActivities = new ArrayList<>();
    private ArrayList<User> projectUsers = new ArrayList<>();

    // Project variables
    private String title;
    private String id;
    private CustomCalendar startDate;
    private User projectLeader;

    protected Project(String title, ProjectPlanner projectPlanner) {
        this.title = title;
        this.projectPlanner = projectPlanner;
        id = genNextId();
    }

    // Create or add functions

    protected void createActivity(String title) throws Exception {
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

    protected void addUserToProject(User user) throws Exception {
        if (isProjectLeaderLoggedIn()) {
            if (user instanceof Administrator) {
                throw new Exception("Not allowed for administrator user");
            }
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

    protected void removeUserFromProject(User user) throws Exception {
        if (isProjectLeaderLoggedIn()) {
            if (!projectUsers.remove(user)) // Returns boolean wether remove was possible
                throw new Exception("User is not in the project");
        } else {
            throw new Exception("Project leader login is required");
        }
    }

    protected void deleteActivity(Activity activity) throws Exception {
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

        return String.valueOf(projectPlanner.dateServer.getDate().getYear()) + "-" + String.valueOf(maxIdInUse);

    }

    private boolean isActivityTitleUnique(String title) {
        for (Activity activity : projectActivities) {
            if (activity.getTitle().equalsIgnoreCase(title)) {
                return false;
            }
        }
        return true;
    }

    protected boolean isProjectLeaderLoggedIn() {
        return projectLeader == projectPlanner.getLoggedIn() && projectLeader != null;
    }

    // Set functions

    protected void setStartDate(int year, int month, int day) throws Exception {
        if (isProjectLeaderLoggedIn()) {
            startDate = new CustomCalendar(year, month, day);
        } else {
            throw new Exception("Project leader login is required");
        }
    }

    protected void setProjectLeader(User user) throws Exception {
        if (projectPlanner.isAdministratorLoggedIn()) {
            projectLeader = user;
        } else {
            throw new Exception("Administrator login is required");
        }
    }

    // Get functions

    protected String getId() {
        return id;
    }

    protected String getTitle() {
        return title;
    }

    protected CustomCalendar getStartDate() {
        return startDate;
    }

    protected ArrayList<User> getProjectEmployees() {
        return projectUsers;
    }


    protected User getProjectleader() {
        return projectLeader;
    }

    protected ArrayList<Activity> getActivities() {
        return projectActivities;
    }

    protected Activity getActivity(String title) throws Exception {
        for (Activity activity : getActivities()) {
            if (activity.getTitle().equalsIgnoreCase(title)) {
                return activity;
            }
        }
        throw new Exception("Activity does not exist in the project");
    }

    // JUNIT Helpfunctions

    // public void cucumberAddUserToProject(User user) throws Exception {
    // projectUsers.add(user);
    // }

    // public void cucumberSetProjectLeader(User user) {
    // projectLeader = user;
    // }

    // public void CucumberCreateActivity(String title) {
    // projectActivities.add(new Activity(title, this));
    // }
}
