package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class main {

    public static void main(String[] args) {
        MainController controller = new MainController();
        try {
            controller.projectPlanner.logIn("HUBE", "PW1234");
            controller.projectPlanner.createEmployee("FRED");
            controller.projectPlanner.createProject("Programmeringsprojekt");
            Project project = controller.projectPlanner.getProject("2022-1");

            project.setProjectLeader(controller.projectPlanner.getUser("FRED"));
            controller.projectPlanner.logIn("FRED", "01234");
            project.addUserToProject(controller.projectPlanner.getUser("FRED"));

            project.createActivity("PisMigIØret");
            project.createActivity("Analyse");
            project.createActivity("lolcat");

            project.getActivity("Analyse").setStartDate(2022, 7);
            project.getActivity("Analyse").setEndDate(2022, 8);
            project.getActivity("PisMigIØret").setStartDate(2021,1);
            project.getActivity("PisMigIØret").setEndDate(2022, 8);

            project.getActivity("Analyse")
                    .addUserToActivity(controller.projectPlanner.getUser("FRED"));
            project.getActivity("PisMigIØret")
                    .addUserToActivity(controller.projectPlanner.getUser("FRED"));
        } catch (Exception e) {
        }
        controller.show();
    }
}
