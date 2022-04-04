package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Project {

    String title;
    static int idIncrement = 0;
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
