package dtu.calculator;

import java.util.ArrayList;

public class User {

    protected String initials;
    protected String password;

    protected ArrayList<Registration> registration = new ArrayList<>();
    ArrayList<Activity> activities = new ArrayList<>();

    public User(String initials, String password) {
        this.initials = initials;
        this.password = password;
    }

    public User(String initials) {
        this.initials = initials;
        this.password = generatePassword();
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



    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public ArrayList<String> getActivitiesFromOtherEmployee(String otherUserInitials) throws Exception {
        ArrayList<String> activitiesTitle = new ArrayList<>();
        for (Activity activity : ProjectPlanner.getUser(otherUserInitials).getActivities()) {
            activitiesTitle.add(activity.getTitle());
        }
        return activitiesTitle;
    }

}
