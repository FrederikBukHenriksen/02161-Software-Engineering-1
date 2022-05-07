package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

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
        for (Registration registration : ProjectPlanner.getUser(otherUserInitials).getRegistration()) {
            if (registration instanceof Leave) {
                activitiesTitle.add(((Leave) registration).getLeaveTitle());
            }
        }
        return activitiesTitle;
    }
    
    public ArrayList<Leave> getLeaves() throws Exception {
        ArrayList<Leave> leaves = new ArrayList<>();
        for (Registration registration : registration) {
            if (registration instanceof Leave) {
                leaves.add(((Leave) registration));
            }
        }
        if(leaves.isEmpty()) {
            throw new Exception("No leaves");
        }
        return leaves;
    }

    public Leave getLeave(String leaveTitle) throws Exception {
        for (Leave leave : getLeaves()) {
            if (leave.getLeaveTitle().equals(leaveTitle)) {
                return leave;
            }
        }   
        throw new Exception("The leave you are looking for does not exist");
    }
    //     public ArrayList<String> getLeaveTitles() {
    //     ArrayList<String> leaveTitles = new ArrayList<>();
    //     for (Registration registration : registration) {
    //         if (registration instanceof Leave) {
    //             leaveTitles.add(((Leave) registration).getLeaveTitle());
    //         }
    //     }
    //     return leaveTitles;
    // }

    public void createLeave(GregorianCalendar startDate, GregorianCalendar endDate, String leaveTitle) {
        registration.add(new Leave(startDate, endDate, leaveTitle));
    }
    
    public ArrayList<Registration> getRegistration() {
        return registration;
    }
    

}
