package dtu.calculator;

public class Controller {

    ProjectPlanner projectPlanner;

    public Controller() throws Exception {
        ProjectPlanner projectPlanner = new ProjectPlanner();
        projectPlanner.addEmployee("Frederik");

        projectPlanner.createProject("Uni");
        projectPlanner.projects.get(0).createActivity("Analyse");
        projectPlanner.projects.get(0).activities.get(0).addEmployee(projectPlanner.employees.get(0));

        System.out.println(projectPlanner.employees);

    }

}
