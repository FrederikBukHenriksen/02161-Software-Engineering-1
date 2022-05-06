package dtu.calculator;

import java.util.ArrayList;

public abstract class User {

    // Contained
    ProjectPlanner projectPlanner;

    protected String initials;
    protected String password;

    protected ArrayList<Registration> registration = new ArrayList<>();
    ArrayList<Activity> activities = new ArrayList<>();

    public User(String initials, String password) {
        this.initials = initials;
        this.password = password;
    }

    public User(String initials, ProjectPlanner projectPlanner) {
        this.initials = initials;
        this.password = generatePassword();
        this.projectPlanner = projectPlanner;
    }

    public void setPassword(String password) {
        password = password;
    }

    public String generatePassword() {
        return "01234";
    }

    public String getInitials() {
        return initials;
    }

    public String getPassword() {
        return password;
    }


    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public ArrayList<String> getActivitiesFromOtherEmployee(String otherUserInitials) throws Exception {
        ArrayList<String> activitiesTitle = new ArrayList<>();
        for (Activity activity : projectPlanner.getUser(otherUserInitials).getActivities()) {
            activitiesTitle.add(activity.getTitle());
        }
        return activitiesTitle;
    }

}
