package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class main {

    private static void main(String[] args) {
        MainController controller = new MainController();
        try {
            ProjectPlanner.logIn("HUBE", "PW1234");
            controller.projectPlanner.addEmployee("FRED");
            controller.projectPlanner.addEmployee("GUST");

            controller.projectPlanner.createProject("Programmeringsprojekt");
            Project project = controller.projectPlanner.getProject("2022-1");

            project.setProjectLeader(controller.projectPlanner.getUser("FRED"));
            controller.projectPlanner.logIn("FRED", "01234");
            project.addUserToProject(controller.projectPlanner.getUser("FRED"));

            project.createActivity("activity_1");
            project.createActivity("activity_2");
            project.createActivity("activity_3");

            project.getActivity("activity_1").setStartDate(2022, 7);
            project.getActivity("activity_2").setEndDate(2022, 8);
            project.getActivity("activity_2").setStartDate(2021, 1);
            project.getActivity("activity_2").setEndDate(2022, 8);

            project.getActivity("activity_1")
                    .addEmployeeToActivity(ProjectPlanner.getUser("FRED"));
            project.getActivity("activity_2")
                    .addEmployeeToActivity(ProjectPlanner.getUser("FRED"));
        } catch (Exception e) {
        }
        controller.show();
    }
}
