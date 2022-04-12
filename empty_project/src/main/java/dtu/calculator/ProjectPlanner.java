package dtu.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public class ProjectPlanner {
    private ArrayList<Project> projects = new ArrayList<>();
    public ArrayList<User> users = new ArrayList<>();

    private User loggedIn;

    public ProjectPlanner() {
        users.add(new Administrator("HUBE", "PW1234")); // Create the administrator profile.
    }

    public void login(String id, String password) {

    }

    public void createProject(String title) {
        projects.add(new Project(title));
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

    public void removeEmployee(Employee employee) {
        users.remove(employee);
    }

    public void logIn(String initals, String password) throws Exception {
        boolean checker = false;
        for (User employee : users) {
            if (employee.initials.equalsIgnoreCase(initals) && employee.password.equals(password)) {
                loggedIn = employee;
                checker = true;
            }
        
        }
        if (!checker){
            throw new Exception("Wrong id or password");
            
        }
    }

    public void logOut() {
        loggedIn = null;
    }

    public User getUser(String initials) throws Exception{
        User found = null;
        for (User user : users) {
            if(user.initials.equalsIgnoreCase(initials)){
                found = user;
            }
        } if(found != null){
        return found;
        } else {
            throw new Exception("User not found");
        }
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

}
