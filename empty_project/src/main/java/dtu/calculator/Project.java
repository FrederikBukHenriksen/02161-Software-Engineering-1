package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Project {

    String title;
    int id;
    GregorianCalendar startTime;
    Employee projectLeader;
    ArrayList<Activity> activities = new ArrayList<>();

    public Project(String title) {
        this.title = title;
        id = getNextId();
    }

    public void createActivity(String title) {
        if (uniqueTitle(title)) {
            activities.add(new Activity(title));
        } else {
            // throw new Exception("Tile is already in use by another activity.");
        }
    }

    public void addActivity(String title) throws Exception {
        if (true) { // placeholder------------------------------------------------------
            if (uniqueTitle(title)) {
                activities.add(new Activity(title));
            } else {
                throw new Exception("The activity already exists");
            }
        } else {
            throw new Exception("Project leader login is required");
        }
    }

    private boolean uniqueTitle(String title) {
        for (Activity act : activities) {
            if (act.title.equalsIgnoreCase(title)) {
                return false;
            }
        }
        return true;
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    private int getNextId() {
        return 0;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

}
