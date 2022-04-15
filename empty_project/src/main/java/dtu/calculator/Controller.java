package dtu.calculator;

import java.util.ArrayList;
import java.util.Arrays;

public class Controller {

    ProjectPlanner projectPlanner = new ProjectPlanner();
    View view = new View();

    public Controller() {
        DateServer.setDate(2022, 1, 1);
        logIn();
        mainMenu();

    }

    public void logIn() {
        // ArrayList login = view.logIn();
        ArrayList login = view.insertMenu("Login", new ArrayList<>(Arrays.asList("Initials: ", "Password: ")), false);

        try {
            ProjectPlanner.logIn((String) login.get(0), (String) login.get(1));
            view.clearScreen();
            view.announcement("Login", view.ANSI_GREEN, new ArrayList<>(
                    Arrays.asList("Login succeeded", "You are logged in as: " + ProjectPlanner.loggedIn.initials)));
            view.enterToContinue();
            view.clearScreen();
            System.out.println("LOLCAT");
        } catch (Exception e) {
            view.error(e);
            view.enterToContinue();
            view.clearScreen();
            logIn();
        }
    }

    public void mainMenu() {
        view.clearScreen();

        if (ProjectPlanner.administratorLoggedIn()) {
            int choice = view.choiceMenu("Main menu",
                    new ArrayList<>(Arrays.asList("Create project", "Delete project", "Edit project", "Add employee",
                            "Edit employee")),
                    false);
            switch (choice) {
                case 1:
                    createProject();
                    break;
                case 2:
                    deleteProject();
                    break;
                case 4:
                    addEmployee();

                default:
                    mainMenu();

            }
        }
    }

    public void createProject() {
        String input = view.insertMenu("Create project", new ArrayList<>(Arrays.asList("Project title: ")), true)
                .get(0);
        if (!input.equals("0")) {
            try {
                projectPlanner.createProject(input);
                view.announcement("Create project", view.ANSI_GREEN,
                        new ArrayList<>(Arrays.asList("Project is created")));
            } catch (Exception e) {
                view.error(e);
                view.enterToContinue();
            }
        }
        mainMenu();
    }

    public void deleteProject() {
        ArrayList<String> list = new ArrayList<>();
        for (Project project : ProjectPlanner.getProjects()) {
            list.add(project.title + ", " + project.id);
        }

        int choice = view.choiceMenu("Delete project", list, true);

        if (choice == 0) {
            mainMenu();
        }

        if (view.choiceCheckRange(choice, 0, list.size())) {
            deleteProject();
        }

        if (view.choiceCheckRange(choice, 0, list.size())) {
            deleteProject();
        }

        Project chosenProject = ProjectPlanner.getProjects().get(choice - 1);
        projectPlanner.removeProject(chosenProject);
        mainMenu();
    }

    public void addEmployee() {
        String initals = view.insertMenu("Add employee", new ArrayList<>(Arrays.asList("Employee initals: ")), true)
                .get(0);
        if (!initals.equals("0")) {
            try {
                projectPlanner.addEmployee(initals);
            } catch (Exception e) {
                view.error(e);
                view.enterToContinue();
            }
        }
        mainMenu();

    }


}
