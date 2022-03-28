package dtu.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public class ProjectPlanner {
    public ArrayList<Project> projects = new ArrayList<>();
    public ArrayList<Employee> employees = new ArrayList<>();
    public Administrator administrator;

    public ProjectPlanner() {
    }

    User loggedIn;

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
                employees.add(new Employee(initials.toUpperCase()));
            }
        } else {
            throw new Exception("Initals are already in use by another employee.");
        }
    }

    public boolean uniqueInitials(String initials) {
        for (Employee employee : employees) {
            if (employee.initials.equalsIgnoreCase(initials)) {
                return false;
            }
        }
        return true;
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public void logIn(String initals, String password) {
        for (User employee : employees) {
            if (employee.initials.equals(initals) && employee.password.equals(password)) {
                loggedIn = employee;
            }
        }
        if (administrator.initials.equals(initals) && administrator.password.equals(password)) {
            loggedIn = administrator;
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

}
