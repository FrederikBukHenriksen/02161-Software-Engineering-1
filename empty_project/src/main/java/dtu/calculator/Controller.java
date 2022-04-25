package dtu.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Controller {

    ProjectPlanner projectPlanner = new ProjectPlanner();
    View view = new View(this);

    Scanner scanner = new Scanner(System.in);
    Stack menuStack = new Stack();

    final String logIn = "Log in";
    final String logOut = "Log Out";

    final String mainMenu = "Main menu";
    final String createProject = "Create project";
    final String deleteProject = "Delete project";
    final String selectProject = "Select project";

    final String addEmployee = "Add employee";
    final String removeEmployee = "Remove employee";

    public Controller() {
        DateServer.setDate(2022, 1, 1);
        menuStackPush(logIn);
        while (!menuStack.empty()) { // Essentially runs as long as the program.
            menuStackDecode(menuStackPeek());
        }
    }

    public void menuStackDecode(String menu) {
        view.clearScreen();
        switch (menu) {
            case logIn:
                logIn();
                break;
            case logOut:
                logOut();
                break;
            case mainMenu:
                mainMenu();
                break;
            case createProject:
                createProject();
                break;
            case deleteProject:
                deleteProject();
                break;
            case addEmployee:
                addEmployee();
                break;
            case selectProject:
                selectProject();
            default:
                break;
        }

    }

    public String consoleInput() {
        String input = scanner.next();
        return input;
    }

    public String consoleInputWithBack() throws BackException {
        String input = "";
        input = scanner.next();
        if (input.equals("back")) {
            menuStackPop();
            menuStackDecode(menuStackPeek());
            throw new BackException();
        }
        return input;
    }

    public void enterToContinue() {
        boolean flag = false;
        while (flag == false) {
            String input = scanner.nextLine();
            System.out.println(input);
            if (input.equals("")) {
                flag = true;
            }
        }
    }

    public void menuStackPush(String menu) {
        menuStack.push(menu);
    }

    public String menuStackPop() {
        return menuStack.pop().toString();
    }

    public String menuStackPeek() {
        return menuStack.peek().toString();
    }

    public void menuStackClear() {
        menuStack.clear();
    }

    public void logIn() {
        view.menu(logIn, new ArrayList<>(Arrays.asList("Initials: ")));
        String initials = consoleInput();
        view.menu(logIn, new ArrayList<>(Arrays.asList("Initials: " + initials, "Password: ")));
        String password = consoleInput();
        try {
            ProjectPlanner.logIn(initials, password);
            menuStackPush(mainMenu);
        } catch (Exception e) {
            view.error(e);
            enterToContinue();
            menuStackPush(logIn);
        }
    }

    public void mainMenu() {

        view.clearScreen();

        if (ProjectPlanner.administratorLoggedIn()) {
            ArrayList<String> menu = new ArrayList<>(
                    Arrays.asList(createProject, deleteProject, addEmployee, selectProject, logOut));
            view.menuEnumerate(mainMenu, menu);
            String choice = consoleInput();
            menuStackPush(menu.get(Integer.parseInt(choice) - 1));
        }
        if (ProjectPlanner.employeeLoggedIn()) {
        }
    }

    public void createProject() {
        view.menu(createProject, new ArrayList<>(Arrays.asList("Project title: ")));
        try {
            String input = consoleInputWithBack();

            projectPlanner.createProject(input);
            menuStackPush(mainMenu);
        } catch (BackException e) {

        } catch (Exception e) {
            view.error(e);
            enterToContinue();
        }
    }

    public void deleteProject() {

        ArrayList<String> UIListOfProjects = new ArrayList<>();
        for (Project project : ProjectPlanner.getProjects()) {
            UIListOfProjects.add(project.title + ", " + project.id);
        }

        view.menuEnumerate(deleteProject, UIListOfProjects);
        try {
            int choice = Integer.parseInt(consoleInputWithBack());

            Project chosenProject = ProjectPlanner.getProjects().get(choice - 1);
            projectPlanner.removeProject(chosenProject);

            menuStackPush(mainMenu);
        } catch (BackException e) {
        }
    }

    public void addEmployee() {
        view.menu(addEmployee, new ArrayList<>(Arrays.asList("Employee initials: ")));
        try {
            String input = consoleInputWithBack();
            projectPlanner.addEmployee(input);
            menuStackPush(mainMenu);
        } catch (BackException e) {
        } catch (Exception e) {
            view.error(e);
        }
    }

    public void removeEmployee() {

        ArrayList<String> UIListOfUsers = new ArrayList<>();
        for (User user : ProjectPlanner.getUsers()) {
            if (user instanceof Employee) {
                UIListOfUsers.add(user.getInitials());
            }
        }
        view.menuEnumerate(removeEmployee, UIListOfUsers);
        try {
            int choice = Integer.parseInt(consoleInputWithBack());

            Project chosenProject = ProjectPlanner.getProjects().get(choice - 1);
            projectPlanner.removeProject(chosenProject);

            menuStackPush(mainMenu);
        } catch (BackException e) {
        }
    }

    public void selectProject() {
        ArrayList<String> selectProjectMenu = new ArrayList<>(Arrays.asList(deleteProject, addEmployee, logOut));
        view.menuEnumerate(selectProject, selectProjectMenu);
        try {
            String choice = consoleInputWithBack();
            menuStackPush(selectProjectMenu.get(Integer.parseInt(choice) - 1));

        } catch (BackException e) {
        }

    }

    public void logOut() {
        projectPlanner.logOut();
        menuStackClear();
        menuStackPush(logIn);
    }

}
