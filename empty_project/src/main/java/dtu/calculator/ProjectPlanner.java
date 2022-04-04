package dtu.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public class ProjectPlanner {
    private ArrayList<Project> projects = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    private User loggedIn;

    public ProjectPlanner() {
        users.add(new Administrator("HUBE", "PW1234")); // Create the administrator profile.
    }

    public void createProject(String title) throws Exception {
        if (administratorLoggedIn()) {
            projects.add(new Project(title));
        } else {
            System.out.println("Lolcat");
            throw new Exception("Administrator login is required");
        }
    }

    public void removeProject(Project project) {
        projects.remove(project);
    }

    public void addEmployee(String initials) throws Exception {
        if (administratorLoggedIn()) {
            if (uniqueInitials(initials)) {
                users.add(new Employee(initials.toUpperCase()));
            } else {
                throw new Exception("Employee is already registered");
            }
        } else {
            throw new Exception("Administrator login is required");
        }
    }

    public boolean uniqueInitials(String initials) {
        for (User employee : users) {
            if (employee.initials.equalsIgnoreCase(initials)) {
                return false;
            }
        }
        return true;
    }

    public boolean uniqueProject(String title, int id) {
        for (Project project : projects) {
            if (project.title.equalsIgnoreCase(title) && project.id == id) {
                return false;
            }
        }
        return true;
    }

    public void removeEmployee(Employee employee) {
        users.remove(employee);
    }

    public void logIn(String initals, String password) {
        for (User employee : users) {
            if (employee.initials.equals(initals) && employee.password.equals(password)) {
                loggedIn = employee;
            }
        }
    }

    public void logOut() {
        loggedIn = null;
    }

    public boolean administratorLoggedIn() {
        if (loggedIn instanceof Administrator) {
            return true;
        }
        return false;
    }

    public boolean employeeLoggedIn() {
        if (loggedIn instanceof Employee) {
            return true;
        }
        return false;
    }

    public User getLoggedIn() {
        return loggedIn;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

}
