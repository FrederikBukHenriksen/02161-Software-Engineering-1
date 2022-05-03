package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ProjectPlanner {
    // Containers
    public static ArrayList<Project> projects = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();

    // Class variables
    GregorianCalendar startTime;
    public static User loggedIn;
    DateServer dateServer;

    public ProjectPlanner() {
        users.add(new Administrator("HUBE", "PW1234")); // Create the administrator profile.
        dateServer = new DateServer(2022, 1, 1);
    }

    // Create or add functions

    public void createProject(String title) throws Exception {
        if (administratorLoggedIn()) {
            projects.add(new Project(title, this));
        } else {
            throw new Exception("Administrator login is required");
        }
    }

    public void addEmployee(String initials) throws Exception {
        if (administratorLoggedIn()) {
            if (initials.length() == 4) {
                if (uniqueUserInitials(initials)) {
                    users.add(new Employee(initials.toUpperCase()));
                } else {
                    throw new Exception("Initals are already in use");
                }
            } else {
                throw new Exception("Initials must be four letters");
            }
        } else {
            throw new Exception("Administrator login is required");
        }
    }

    // Remove or delete functions

    public void removeProject(Project project) throws Exception {
        if (administratorLoggedIn()) {
            projects.remove(project);
        } else {
            throw new Exception("Administrator login is required");
        }
    }

    public void removeEmployee(User user) throws Exception {
        if (administratorLoggedIn()) {
            getUsers().remove(user);
        } else {
            throw new Exception("Administrator login is required");
        }
    }

    // Check and help functions

    public boolean uniqueUserInitials(String initials) {
        for (User user : users) {
            if (user.getInitials().equalsIgnoreCase(initials)) {
                return false;
            }
        }
        return true;
    }

    public boolean uniqueProjectTitleAndId(String title, String id) {
        for (Project project : projects) {
            if (!project.getTitle().equalsIgnoreCase(title) && project.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }

    public static boolean administratorLoggedIn() {
        return (loggedIn instanceof Administrator);
    }

    public static boolean employeeLoggedIn() {
        return (loggedIn instanceof Employee);
    }

    public static void logIn(String initals, String password) throws Exception {
        // Flag instead of check for null for safety reason
        boolean logInSuccesful = false;
        for (User employee : getUsers()) {
            if (employee.getInitials().equalsIgnoreCase(initals) && employee.getPassword().equals(password)) {
                setLoggedIn(employee);
                logInSuccesful = true;
            }
        }
        if (!logInSuccesful) {
            throw new Exception("Wrong id or password");
        }
    }

    public static void logOut() {
        setLoggedIn(null);
    }

    // Set functions

    public static void setLoggedIn(User user) {
        loggedIn = user;
    }

    public void setStartTime(GregorianCalendar calendar) {
        startTime = calendar;
    }

    // Get functions

    public static User getLoggedIn() {
        return loggedIn;
    }

    public static ArrayList<User> getEmployees() {
        ArrayList<User> list = new ArrayList<>();
        for (User user : getUsers()) {
            if (user instanceof Employee) {
                list.add(user);
            }
        }
        return list;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static Project getProject(String search) throws Exception {
        for (Project project : getProjects()) {
            if (project.getId().equalsIgnoreCase(search)) {
                return project;
            }
        }
            throw new Exception("Project does not exist");
        }

        public static User getUser(String initials) throws Exception {
        for (User user : getUsers()) {
            if (user.getInitials().equalsIgnoreCase(initials)) {
                return user;
            }
        }
        throw new Exception("User does not exist");
    }

    public static ArrayList<Project> getProjects() {
        return projects;
    }

    // JUNIT Helpfunctions
    public static void cucumberAddEmployee(String initials) {
        users.add(new Employee(initials));
    }

    public void cucumberCreateProject(String title) {
        projects.add(new Project(title, this));
    }

    public static void addAdministrator() {
        users.add(new Administrator("HUBE", "PW1234"));
    }

}
