package dtu.calculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    protected void registerWork(CustomCalendar startTime, CustomCalendar endTime, Activity activity)
            throws Exception {
        if (this instanceof Administrator) {
            throw new Exception("Not allowed for administrator user");
        }
        if (!this.equals(projectPlanner.getLoggedIn()) && !activity.project.isProjectLeaderLoggedIn()) {
            throw new Exception("Cannot register work for other users");
        }
        registrations.add(new Work(startTime, endTime, projectPlanner,activity));
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

    public ArrayList<Activity> getEmployeeActivities() {
        ArrayList<Activity> employeeActivities = new ArrayList<>();
        for (Project project : projectPlanner.getProjects()) {
            for (Activity activity : project.getActivities()) {
                for (User employee : activity.getEmployees()) {
                    if (this.equals(employee)) {
                        employeeActivities.add(activity);
                    }
                }
            }
        }
        // // Comparator in order to sort by startTime.
        // Collections.sort(employeeActivities, new Comparator<Activity>() {
        // @Override
        // // TODO: lav tid evaluering.
        // public int compare(Activity a1, Activity a2) {

        // return a1.getStartDate().compareTo(a2.getStartDate());
        // // return a1.getTitle().compareTo(a2.getTitle());
        // }
        // });
        return employeeActivities;
    }

    public ArrayList<String> getLeaveTitles() throws Exception {
        ArrayList<String> leaveTitles = new ArrayList<>();

        for (Registration registration : registrations) {
            if (registration instanceof Leave) {
                leaveTitles.add(((Leave) registration).getLeaveTitle());
            }
        }
        if (leaveTitles.isEmpty()) {
            throw new Exception("No leaves");
        }
        return leaveTitles;
    }

    public ArrayList<Leave> getLeaveAll() throws Exception {
        ArrayList<Leave> leave = new ArrayList<>();
        for (Registration registration : registrations) {
            if (registration instanceof Leave) {
                leave.add(((Leave) registration));
            }
        }
        if (leave.isEmpty()) {
            throw new Exception("No leaves");
        }
        return leave;
    }

    public Leave getLeave(String leaveTitle) throws Exception {
        for (Registration registration : registrations) {
            if (registration instanceof Leave) {
                if (((Leave) registration).getLeaveTitle().equals(leaveTitle)) {
                    return (Leave) registration;
                }
            }
        }
        throw new Exception("Leave does not exist");
    }


    public void createLeave(CustomCalendar startDate, CustomCalendar endDate, String leaveTitle) throws Exception {
        registrations.add(new Leave(startDate, endDate, projectPlanner,leaveTitle));
    }

}
