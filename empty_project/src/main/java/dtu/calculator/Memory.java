package dtu.calculator;

/**
 * clearMemory
 */
// public class Memory {

// public static void reset() {
// // ProjectPlanner.loggedIn = null;
// ProjectPlanner.users = new ArrayList<User>();
// ProjectPlanner.projects = new ArrayList<Project>();
// // ProjectPlanner.addAdministrator(); // Re-add administrator.
// System.out.println("ALT CLEARED");
// }

// }

public class Memory {

    public final ProjectPlanner projectPlanner = new ProjectPlanner();

    public static void reset() {
        // projectPlanner = new ProjectPlanner();
        System.out.println("MEMORY: ALT CLEARED");
    }

}