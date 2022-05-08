package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ProjectPlanner {
    // Containers
    public ArrayList<Project> projects = new ArrayList<>();
    public ArrayList<User> users = new ArrayList<>();

    // Class variables
    public User loggedIn;
    public DateServer dateServer = new DateServer(2022, 1, 1);

    public ProjectPlanner() {
        users.add(new Administrator("HUBE", "PW1234", this)); // Create the administrator profile.
    }

    // Create or add functions

    protected void createProject(String title) throws Exception {
        if (isAdministratorLoggedIn()) {
            projects.add(new Project(title, this));
        } else {
            throw new Exception("Administrator login is required");
        }
    }

    protected void createEmployee(String initials) throws Exception {
        if (isAdministratorLoggedIn()) {
            if (initials.length() == 4) {
                if (uniqueUserInitials(initials)) {
                    users.add(new Employee(initials.toUpperCase(), this));
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

    protected void deleteProject(Project project) throws Exception {
        if (isAdministratorLoggedIn()) {
            projects.remove(project);
        } else {
            throw new Exception("Administrator login is required");
        }
    }

    protected void deleteEmployee(User user) throws Exception {
        if (isAdministratorLoggedIn()) {
            if (!(user instanceof Administrator)) {
            getUsers().remove(user);
        } else {
            throw new Exception("Cannot delete administrator profile");
        }
    } else {
            throw new Exception("Administrator login is required");
        }
    }

    // Check and help functions

    private boolean uniqueUserInitials(String initials) {
        for (User user : users) {
            if (user.getInitials().equalsIgnoreCase(initials)) {
                return false;
            }
        }
        return true;
    }

    private boolean uniqueProjectTitleAndId(String title, String id) {
        for (Project project : projects) {
            if (!project.getTitle().equalsIgnoreCase(title) && project.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }

    protected boolean isAdministratorLoggedIn() {
        return (loggedIn instanceof Administrator);
    }

    protected boolean isEmployeeLoggedIn() {
        return (loggedIn instanceof Employee);
    }

    public void logIn(String initals, String password) throws Exception {
        // Flag instead of check for null for safety reason
        boolean logInSuccesful = false;
        for (User user : getUsers()) {
            if (user.getInitials().equalsIgnoreCase(initals) && user.getPassword().equals(password)) {
                setLoggedIn(user);
                logInSuccesful = true;
                return;
            }
        }
        if (!logInSuccesful) {
            throw new Exception("Wrong id or password");
        }
    }

    public void logOut() {
        setLoggedIn(null);
    }

    // Set functions

    private void setLoggedIn(User user) {
        loggedIn = user;
    }

    // Get functions

    protected User getLoggedIn() {
        return loggedIn;
    }

    protected ArrayList<User> getEmployees() {
        ArrayList<User> list = new ArrayList<>();
        for (User user : getUsers()) {
            if (user instanceof Employee) {
                list.add(user);
            }
        }
        return list;
    }

    protected ArrayList<User> getUsers() {
        return users;
    }

    protected Project getProject(String search) throws Exception {
        for (Project project : getProjects()) {
            if (project.getId().equalsIgnoreCase(search)) {
                return project;
            }
        }
            throw new Exception("Project does not exist");
        }

        public User getUser(String initials) throws Exception {
        for (User user : getUsers()) {
            if (user.getInitials().equalsIgnoreCase(initials)) {
                return user;
            }
        }
        throw new Exception("User does not exist");
    }

    protected ArrayList<Project> getProjects() {
        return projects;
    }

    // JUNIT Helpfunctions
    protected void cucumberAddEmployee(String initials) {
        users.add(new Employee(initials, this));
    }

    protected void cucumberCreateProject(String title) {
        projects.add(new Project(title, this));
    }

    protected void cucumberCreateAdministrator(String initials) {
        users.add(new Administrator("HUBE", "PW1234", this));
    }

}
