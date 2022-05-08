package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public abstract class User {

    // Contained
    protected ProjectPlanner projectPlanner;

    // Contains

    protected ArrayList<Registration> registrations = new ArrayList<>();

    protected String initials;
    protected String password;

    protected User(String initials, ProjectPlanner projectPlanner) {
        this.initials = initials;
        this.password = generatePassword();
        this.projectPlanner = projectPlanner;
    }

    protected GregorianCalendar createTimestamp(int year, int month, int date, int hour, int minute) {
        return new GregorianCalendar(year, month, date, hour, minute);
    }

    protected void registerWork(GregorianCalendar startTime, GregorianCalendar endTime, Activity activity)
            throws Exception {
        if (this instanceof Administrator) {
            throw new Exception("Not allowed for administrator user");
        }
        if (!this.equals(projectPlanner.getLoggedIn()) && !activity.project.isProjectLeaderLoggedIn()) {
            throw new Exception("Cannot register work for other users");
        }
        registrations.add(new Work(startTime, endTime, activity));
    }

    protected void setPassword(String password) {
        password = password;
    }

    private String generatePassword() {
        // TODO: Some kind of passwordgeneration algorithem is required.
        return "01234";
    }

    protected ArrayList<Work> getWorkRegistrations() {
        ArrayList<Work> list = new ArrayList<>();
        for (Registration registration : getRegistrations()) {
            if (registration instanceof Work) {
                list.add((Work) registration);
            }
        }
        return list;
    }

    protected String getInitials() {
        return initials;
    }

    protected String getPassword() {
        return password;
    }

    protected ArrayList<Registration> getRegistrations() {
        return registrations;
    }
}
