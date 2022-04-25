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

    Project selectedProject;

    final String logIn = "Log in";
    final String logOut = "Log Out";

    final String mainMenu = "Main menu";

    final String createProject = "Create project";

    // Projektmenu
    final String selectProject = "Select project";

    final String deleteProject = "Delete project";

    final String changeProjectDate = "Change project date";

    final String addActivity = "Create Activity";

    final String addEmployeeToProject = "Add employee to project";
    final String removeEmployeeToProject = "Remove employee from project";

    // Activitymenu
    final String removeActivity = "Remove Activity";

    final String addEmployeeToActivity = "Add employee to activity";
    final String removeEmployeeToActivity = "Remove employee from activity";

    final String setActivityEstimate = "Set activity estimate";
    final String changeActivityWeek = "Change activity week";

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
            case selectProject:
                selectProject();
                break;
            case addEmployee:
                addEmployee();
                break;
            case removeEmployee:
                removeEmployee();
                break;
            case changeProjectDate:
                changeProjectDate();
                break;

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
        ArrayList<String> menu = new ArrayList<>();
        if (ProjectPlanner.administratorLoggedIn()) {
            menu = new ArrayList<>(
                    Arrays.asList(createProject, deleteProject, selectProject, addEmployee, removeEmployee, logOut));
        } else if (ProjectPlanner.employeeLoggedIn()) {

        }

            view.menuEnumerate(mainMenu, menu);
            String choice = consoleInput();
            menuStackPush(menu.get(Integer.parseInt(choice) - 1));

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

        ArrayList<String> UIListOfEmployees = new ArrayList<>();
        for (User user : ProjectPlanner.getEmployees()) {
            UIListOfEmployees.add(user.initials);
        }
        view.menuEnumerate(removeEmployee, UIListOfEmployees);
        try {
            int choice = Integer.parseInt(consoleInputWithBack());

            User chosenEmployee = ProjectPlanner.getEmployees().get(choice - 1);
            projectPlanner.removeEmployee(chosenEmployee);

            menuStackPush(mainMenu);
        } catch (BackException e) {
        } catch (Exception e) {
            view.error(e);
        }
    }

    public void selectProject() {
        selectedProject = null;

        ArrayList<String> UIListOfProjects = new ArrayList<>();
        for (Project project : ProjectPlanner.getProjects()) {
            UIListOfProjects.add(project.title + ", " + project.id);
        }

        view.menuEnumerate(selectProject, UIListOfProjects);
        try {
            int choice = Integer.parseInt(consoleInputWithBack());

            this.selectedProject = ProjectPlanner.getProjects().get(choice - 1);

            ArrayList<String> menu = new ArrayList<>(
                    Arrays.asList(changeProjectDate, addActivity, addEmployeeToProject,
                            removeEmployeeToProject, deleteProject));
            view.menuEnumerate(mainMenu, menu);
            choice = Integer.parseInt(consoleInputWithBack());
            menuStackPush(menu.get(choice - 1));

        } catch (BackException e) {
        }

    }

    public void changeProjectDate() {
        try {
            view.menu(changeProjectDate, new ArrayList<>(Arrays.asList("Type day: ")));
            int day = Integer.parseInt(consoleInputWithBack());
            view.menu(changeProjectDate, new ArrayList<>(Arrays.asList("Type month: ")));
            int month = Integer.parseInt(consoleInputWithBack());
            view.menu(changeProjectDate, new ArrayList<>(Arrays.asList("Type year: ")));
            int year = Integer.parseInt(consoleInputWithBack());
            selectedProject.setStartDate(day, month, year);
        } catch (BackException e) {
        }

        menuStackPush(mainMenu);
    }

    public void logOut() {
        projectPlanner.logOut();
        menuStackClear();
        menuStackPush(logIn);
    }

}
