package dtu.calculator;

import java.util.ArrayList;

/**
 * clearMemory
 */
public class Memory {

    public static void reset() {
        ProjectPlanner.loggedIn = null;
        ProjectPlanner.users = new ArrayList<User>();
        ProjectPlanner.projects = new ArrayList<Project>();
        ProjectPlanner.addAdministrator(); // Re-add administrator.
        System.out.println("ALT CLEARED");
    }

}