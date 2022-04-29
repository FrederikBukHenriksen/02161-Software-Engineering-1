package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

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

            project.createActivity("PisMigIØret");
            project.createActivity("Analyse");
            project.createActivity("lolcat");

            project.getActivity("Analyse").startTime = new GregorianCalendar(2022, 7, 1);
            project.getActivity("Analyse").endTime = new GregorianCalendar(2022, 9, 1);

            project.getActivity("PisMigIØret").startTime = new GregorianCalendar(2022, 6, 1);
            project.getActivity("PisMigIØret").endTime = new GregorianCalendar(2022, 10, 1);

            project.getActivity("Analyse")
                    .addEmployeeToActivity(ProjectPlanner.getUser("FRED"));
            project.getActivity("PisMigIØret")
                    .addEmployeeToActivity(ProjectPlanner.getUser("FRED"));
        } catch (Exception e) {
            ;
        }
        controller.show();
    }
}
