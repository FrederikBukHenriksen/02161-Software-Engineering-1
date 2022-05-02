package dtu.calculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;

public class Employee extends User {

    public Employee(String initials) {
        super(initials);
    }

    public ArrayList<Activity> getEmployeeActivities() {
        ArrayList<Activity> employeeActivities = new ArrayList<>();
        for (Project project : ProjectPlanner.getProjects()) {
            for (Activity activity : project.getActivities()) {
                for (User employee : activity.getEmployees()) {
                    if (ProjectPlanner.getLoggedIn().equals(employee)) {
                        employeeActivities.add(activity);

                    }
                }
            }
        }
        // Comparator in order to sort by startTime.
        Collections.sort(employeeActivities, new Comparator<Activity>() {
            @Override
            // TODO: lav tid evaluering.
            public int compare(Activity a1, Activity a2) {

                return a1.startTime.compareTo(a2.startTime);
                // return a1.getTitle().compareTo(a2.getTitle());
            }
        });
        return employeeActivities;
    }

    public void registerWork(GregorianCalendar startTime, GregorianCalendar endTime, Activity activity) {
        registration.add(new Work(startTime, endTime, activity));
    }


}
