package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public abstract class User {

    // Contained
    ProjectPlanner projectPlanner;

    protected String initials;
    protected String password;

    protected ArrayList<Registration> registrations = new ArrayList<>();
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

    public GregorianCalendar createTimestamp(int year, int month, int date, int hour, int minute) {
        return new GregorianCalendar(year, month, date, hour, minute);
    }

    public void registerWork(GregorianCalendar startTime, GregorianCalendar endTime, Activity activity)
            throws Exception {
        if (this instanceof Administrator) {
            throw new Exception("Not allowed for administrator user");
        }
        if (!this.equals(projectPlanner.getLoggedIn()) && !activity.project.isProjectLeaderLoggedIn()) {
            throw new Exception("Cannot register work for other users");
        }
        registrations.add(new Work(startTime, endTime, activity));
    }

    public void setPassword(String password) {
        password = password;
    }

    public String generatePassword() {
        return "01234";
    }

    public ArrayList<Work> getWorkRegistrations() {
        ArrayList<Work> list = new ArrayList<>();
        for (Registration registration : getRegistrations()) {
            if (registration instanceof Work) {
                list.add((Work) registration);
            }
        }
        return list;
    }

    public String getInitials() {
        return initials;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Registration> getRegistrations() {
        return registrations;
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
