package dtu.calculator;

import java.util.ArrayList;
import java.util.List;

public class ProjectPlanner {
    public ArrayList<Project> projects = new ArrayList<>();
    public ArrayList<Employee> employees = new ArrayList<>();
    public Administrator administrator;

    public ProjectPlanner() {
    }

    User loggedIn;

    public void createProject(String title) {
        projects.add(new Project(title));
    }

    public void removeProject(Project project) {
        projects.remove(project);
    }

    public void addEmployee(String initials) {
        if (uniqueInitials(initials)) {
            employees.add(new Employee(initials.toUpperCase()));
        } else {
            // throw new Exception("Initals are already in use by another employee.");
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
}
