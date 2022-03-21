package dtu.calculator;

import java.util.ArrayList;
import java.util.List;

public class ProjectPlanner {
    ArrayList<Project> projects = new ArrayList<>();
    ArrayList<Employee> employees = new ArrayList<>();
    ArrayList<Administrator> administrators = new ArrayList<>();

    public void createProject(String title) {
        projects.add(new Project(title));
    }

    public void removeProject(Project project) {
        projects.remove(project);
    }

    public void addEmployee(String initials) throws Exception {
        if (uniqueInitials(initials)) {
            employees.add(new Employee(initials.toUpperCase()));
        } else {
            throw new Exception("Initals are already in use by another employee.");
        }
    }

    private boolean uniqueInitials(String initials) {
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
