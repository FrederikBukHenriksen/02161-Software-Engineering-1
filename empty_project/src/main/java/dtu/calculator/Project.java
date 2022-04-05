package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Project {

    String title;
    static int idIncrement = 0;
    int id;
    GregorianCalendar startTime;
    User projectLeader;
    ArrayList<Activity> activities = new ArrayList<>();

    ProjectPlanner projectPlanner;

    public Project(String title, ProjectPlanner projectplanner) {
        this.title = title;
        id = getNextId();

        this.projectPlanner = projectplanner;
    }

    public boolean projectLeaderLoggedIn() {
        if (ProjectPlanner.loggedIn.equals(projectLeader)) {
            return true;
        }
        return false;
    }

    public void createActivity(String title) {
        if (uniqueTitle(title)) {
            activities.add(new Activity(title));
        } else {
            // throw new Exception("Tile is already in use by another activity.");
        }
    }

    private boolean uniqueTitle(String title) {
        for (Activity activity : activities) {
            if (activity.title.equalsIgnoreCase(title)) {
                return false;
            }
        }
        return true;
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    private int getNextId() {
        id = idIncrement;
        idIncrement++;
        return id;
    }

}
