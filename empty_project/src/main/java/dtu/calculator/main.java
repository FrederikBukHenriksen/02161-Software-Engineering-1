package dtu.calculator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import dtu.calculator.controller.MainController;

public class main {

    public static void main(String[] args) {
        DateServer.setDate(2022, 1, 1);

        MainController controller = new MainController();
        try {
            ProjectPlanner.logIn("HUBE", "PW1234");
            controller.projectPlanner.addEmployee("FRED");
            controller.projectPlanner.addEmployee("GUST");

            controller.projectPlanner.createProject("Programmeringsprojekt");
            Project project = controller.projectPlanner.getProject("Programmeringsprojekt");

            project.setProjectLeader(ProjectPlanner.getUser("FRED"));
            ProjectPlanner.logIn("FRED", "01234");
            project.addEmployeeToProject(ProjectPlanner.getUser("FRED"));

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
