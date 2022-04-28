package dtu.calculator;

import java.util.ArrayList;

public class main {

    public static void main(String[] args) {
        DateServer.setDate(2022, 1, 1);

        Controller controller = new Controller();
        try {
            ProjectPlanner.logIn("HUBE", "PW1234");
            controller.projectPlanner.addEmployee("FRED");
            controller.projectPlanner.createProject("Programmeringsprojekt");
            ArrayList<Project> list = ProjectPlanner.getProjects();
            Project project = list.get(0);
            project.addEmployeeToProject("FRED");

            project.setProjectLeader(ProjectPlanner.getUser("FRED"));
            ProjectPlanner.logIn("FRED", "01234");

            project.createActivity("Analyse");
            project.getActivity("Analyse")
                    .addEmployeeToActivity(ProjectPlanner.getUser("FRED"));
            project.createActivity("PisMigIØret");
            project.getActivity("PisMigIØret")
                    .addEmployeeToActivity(ProjectPlanner.getUser("FRED"));
        } catch (Exception e) {
            ;
        }
        controller.show();
    }
}
