package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import dtu.calculator.controller.MainController;

public class main {

    public static void main(String[] args) {
        MainController controller = new MainController();
        try {
            ProjectPlanner.logIn("HUBE", "PW1234");
            controller.projectPlanner.addEmployee("FRED");
            controller.projectPlanner.createProject("Programmeringsprojekt");
            Project project = controller.projectPlanner.getProject("2022-1");

            project.setProjectLeader(ProjectPlanner.getUser("FRED"));
            ProjectPlanner.logIn("FRED", "01234");
            project.addUserToProject(ProjectPlanner.getUser("FRED"));

            project.createActivity("PisMigIØret");
            project.createActivity("Analyse");
            project.createActivity("lolcat");

            project.getActivity("Analyse").setStartDate(2022, 7);
            project.getActivity("Analyse").setEndDate(2022, 8);
            project.getActivity("PisMigIØret").setStartDate(2021,1);
            project.getActivity("PisMigIØret").setEndDate(2022, 8);

            project.getActivity("Analyse")
                    .addUserToActivity(ProjectPlanner.getUser("FRED"));
            project.getActivity("PisMigIØret")
                    .addUserToActivity(ProjectPlanner.getUser("FRED"));
        } catch (Exception e) {
        }
        controller.show();
    }
}
