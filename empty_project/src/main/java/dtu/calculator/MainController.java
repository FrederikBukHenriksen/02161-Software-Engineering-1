package dtu.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Stack;

import org.xml.sax.ErrorHandler;

public class MainController {

    ProjectPlanner projectPlanner;
    ProjectMenu projectMenu;

    View view;

    Scanner scanner = new Scanner(System.in);
    Stack menuStack = new Stack();

    public Project selectedProject = null;
    Activity selectedActivity = null;

    final String logIn = "Log in";
    final String logOut = "Log Out";

    final String mainMenu = "Main menu";

    final String createProject = "Create project";

    final String addEmployee = "Add employee";
    final String removeEmployee = "Remove employee";

    final String registerTime = "Register time";
    final String activityCalendar = "Calendar";

    // Projektmenu
    final String selectProject = "Select project";

    final String deleteProject = "Delete project";

    final String setProjectLeader = "Set project leader";

    final String changeProjectDate = "Change project date";

    final String createActivity = "Create Activity";

    final String addEmployeeToProject = "Add employee to project";
    final String removeEmployeeFromProject = "Remove employee from project";

    // Activitymenu
    final String selectActivity = "Select activity";

    final String removeActivity = "Remove Activity";

    final String addEmployeeToActivity = "Add employee to activity";
    final String removeEmployeeFromActivity = "Remove employee from activity";

    final String setActivityEstimate = "Set activity estimate";
    final String changeActivityStart = "Change activity start";
    final String changeActivityEnd = "Change activity end";

    public MainController() {
        menuStackPush(logIn);
        this.projectPlanner = new ProjectPlanner();
        this.view = new View();
        this.projectMenu = new ProjectMenu(projectPlanner, this);

    }

    public void show() {
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
                projectMenu.selectProject();
                break;
            case addEmployee:
                addEmployee();
                break;
            case removeEmployee:
                removeEmployee();
                break;
            case changeProjectDate:
                projectMenu.changeProjectDate();
                break;

            case createActivity:
                projectMenu.createActivity();
                break;
            case addEmployeeToProject:
                projectMenu.addEmployeeToProject();
                break;
            case removeEmployeeFromProject:
                projectMenu.removeEmployeeFromProject();
                break;
            case selectActivity:
                selectActivity();
                break;
            case setActivityEstimate:
                setActivityEstimate();
            case setProjectLeader:
                projectMenu.setProjectLeader();
                break;
            case activityCalendar:
                activityCalendar();
                break;
            case registerTime:
                registerTime();
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

    public void handleException(Exception exception) {
        view.error(exception);
        pressEnterToContinue();
    }

    public void pressEnterToContinue() {
        boolean flag = false;
        while (flag == false) {
            Scanner enterScanner = new Scanner(System.in);
            String input = enterScanner.nextLine();
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
            projectPlanner.logIn(initials, password);
            menuStackPush(mainMenu);
        } catch (Exception e) {
            handleException(e);
            menuStackPush(logIn);
        }
    }

    public void mainMenu() {

        view.clearScreen();
        ArrayList<String> menu = new ArrayList<>();
        if (projectPlanner.isAdministratorLoggedIn()) {
            menu = new ArrayList<>(
                    Arrays.asList(createProject, selectProject, addEmployee, removeEmployee, logOut));
        } else if (projectPlanner.isEmployeeLoggedIn()) {
            menu = new ArrayList<>(
                    Arrays.asList(selectProject, registerTime, activityCalendar, logOut));

        }

        view.menuEnumerate(mainMenu, menu);
        String choice = consoleInput();
        String menuSelect = menu.get(Integer.parseInt(choice) - 1);
        menuStackPush(menuSelect);

    }

    public void createProject() {
        view.menu(createProject, new ArrayList<>(Arrays.asList("Project title: ")));
        try {
            String input = consoleInputWithBack();

            projectPlanner.createProject(input);
            menuStackPush(mainMenu);
        } catch (BackException e) {

        } catch (Exception e) {
            handleException(e);
        }
    }

    public void deleteProject() {

        ArrayList<String> UIListOfProjects = new ArrayList<>();
        for (Project project : projectPlanner.getProjects()) {
            UIListOfProjects.add(project.getTitle() + ", " + project.getId());
        }

        view.menuEnumerate(deleteProject, UIListOfProjects);
        try {
            int choice = Integer.parseInt(consoleInputWithBack());

            Project chosenProject = projectPlanner.getProjects().get(choice - 1);
            projectPlanner.deleteProject(chosenProject);

            menuStackPush(mainMenu);
        } catch (BackException e) {
        } catch (Exception e) {
        }
    }

    public void addEmployee() {
        view.menu(addEmployee, new ArrayList<>(Arrays.asList("Employee initials: ")));
        try {
            String input = consoleInputWithBack();
            projectPlanner.createEmployee(input);
            menuStackPush(mainMenu);
        } catch (BackException e) {
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void removeEmployee() {

        ArrayList<String> UIListOfEmployees = new ArrayList<>();
        for (User user : projectPlanner.getEmployees()) {
            UIListOfEmployees.add(user.getInitials());
        }
        view.menuEnumerate(removeEmployee, UIListOfEmployees);
        try {
            int choice = Integer.parseInt(consoleInputWithBack());

            User chosenEmployee = projectPlanner.getEmployees().get(choice - 1);
            projectPlanner.deleteEmployee(chosenEmployee);

            menuStackPush(mainMenu);
        } catch (BackException e) {
        } catch (Exception e) {
            handleException(e);
        }
    }

    // project start:

    // project end;
    public void selectActivity() {
        ArrayList<String> UIListOfProjectsActivity = new ArrayList<>();
        for (Activity activity : selectedProject.getActivities()) {
            UIListOfProjectsActivity.add(activity.getTitle());
        }

        view.menuEnumerate(selectActivity, UIListOfProjectsActivity);
        try {

            int choice = Integer.parseInt(consoleInputWithBack());
            selectedActivity = selectedProject.getActivities().get(choice - 1);

            ArrayList<String> menu = new ArrayList<>(
                    Arrays.asList(addEmployeeToActivity, removeEmployeeFromActivity,
                            setActivityEstimate, changeActivityStart, changeActivityEnd, removeActivity));
            view.menuEnumerate(selectActivity, menu);
            choice = Integer.parseInt(consoleInputWithBack());
            menuStackPush(menu.get(choice - 1));
        } catch (BackException e) {
        } catch (Exception e) {
            handleException(e);
        }

    }

    public void addEmployeeToActivity() {
        ArrayList<String> UIListOfProjectEmployees = new ArrayList<>();
        for (User employee : selectedProject.getProjectEmployees()) {
            UIListOfProjectEmployees.add(employee.getInitials());
        }

        try {
            view.menuEnumerate(addEmployeeToActivity, UIListOfProjectEmployees);
            int choice = Integer.parseInt(consoleInputWithBack());
            User chosenEmployee = selectedProject.getProjectEmployees().get(choice - 1);
            selectedActivity.addUserToActivity(chosenEmployee);
            menuStackPush(selectActivity);

        } catch (BackException e) {
        } catch (Exception e) {
            handleException(e);
        }

    }

    public void removeEmployeeFromActivity() {
        ArrayList<String> UIListOfActivityEmployees = new ArrayList<>();
        for (User employee : selectedActivity.getEmployees()) {
            UIListOfActivityEmployees.add(employee.getInitials());
        }
        try {
            view.menuEnumerate(removeEmployeeFromActivity, UIListOfActivityEmployees);
            int choice = Integer.parseInt(consoleInputWithBack());

            User chosenEmployee = selectedActivity.getEmployees().get(choice - 1);
            selectedActivity.removeEmployeeFromActivity(chosenEmployee);
            menuStackPush(selectActivity);
        } catch (BackException e) {
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void setActivityEstimate() {
        view.menu(setActivityEstimate, new ArrayList<>(Arrays.asList("Type estimate: ")));
        try {
            int estimate = Integer.parseInt(consoleInputWithBack());
            selectedActivity.setActivityEstimate(estimate);
            menuStackPush(selectActivity);
        } catch (BackException e) {
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void changeActivityStart() {
        view.menu(changeActivityStart, new ArrayList<>(Arrays.asList("Type acitivity start week: ")));
        try {
            int choice = Integer.parseInt(consoleInputWithBack());
            // TODO: Sæt start tiden
            menuStackPush(selectActivity);
        } catch (BackException e) {
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void changeActivityEnd() {
        view.menu(changeActivityStart, new ArrayList<>(Arrays.asList("Type acitivity end week: ")));
        try {
            int choice = Integer.parseInt(consoleInputWithBack());
            // TODO: Sæt slut tiden
            menuStackPush(selectActivity);
        } catch (BackException e) {
        } catch (Exception e) {
            handleException(e);
        }
    }

    // Employee-menu

    public void activityCalendar() {
        ArrayList<Activity> employeeActivities = ((Employee) projectPlanner.getLoggedIn()).getEmployeeActivities();
            ArrayList<String> UIlistOfActivities = new ArrayList<>();
            for (Activity activity : employeeActivities) {
                UIlistOfActivities.add(activity.getTitle());
            }
            view.menuEnumerate(activityCalendar, UIlistOfActivities);
            String input = consoleInput();
            menuStackPush(mainMenu);

    }
    

    public void registerTime() {
        ArrayList menu = new ArrayList<>(
                Arrays.asList("Activities you are assigned to", "Projects you are a part of", "All projects"));
        view.menuEnumerate(registerTime, menu);
        try {
            int choice = Integer.parseInt(consoleInputWithBack());

            // Find the selectable activities according to the choice.
            ArrayList<Activity> listOfActivities = new ArrayList<>();
            switch (choice) {
                case 1:
                    listOfActivities = ((Employee) projectPlanner.getLoggedIn()).getEmployeeActivities();

                    break;
                case 2:
                    for (Project project : projectPlanner.getProjects()) {
                        if (project.getProjectEmployees().contains(projectPlanner.loggedIn)) {
                            for (Activity activity : project.getActivities()) {
                                listOfActivities.add(activity);
                            }
                        }
                    }
                    break;
                case 3:
                    for (Project project : projectPlanner.getProjects()) {
                        for (Activity activity : project.getActivities()) {
                            listOfActivities.add(activity);
                        }
                    }
                    break;
                default:
                    break;
            }

            // Let the user select an activity
            ArrayList<String> listOfActivitiesString = new ArrayList<>();
            for (Activity activity : listOfActivities) {
                listOfActivitiesString.add(activity.getTitle());
            }
            view.menuEnumerate(registerTime, listOfActivitiesString);
            choice = Integer.parseInt(consoleInputWithBack());
            Activity chosenActivity = listOfActivities.get(choice - 1);

            ArrayList<Integer> startCal = setDateWithTime(registerTime + " start time");
            GregorianCalendar startTime = Work.calendarWork(startCal.get(4),
                    startCal.get(
                            3),
                    startCal.get(2), startCal.get(1), startCal.get(0));
            ArrayList<Integer> endCal = setDateWithTime(registerTime + " end time");
            GregorianCalendar endTime = Work
                    .calendarWork(startCal.get(4),
                            startCal.get(
                                    3),
                            startCal.get(2), startCal.get(1), startCal.get(0));

            ((Employee) projectPlanner.getLoggedIn()).registerWork(startTime, endTime, chosenActivity);
        } catch (BackException e) {
        } catch (Exception e) {
            handleException(e);
        }

    }

    public void logOut() {
        projectPlanner.logOut();
        menuStackClear();
        menuStackPush(logIn);
    }

    // Help-functions
    public ArrayList<Integer> setDateWithTime(String title) {
        ArrayList<Integer> list = new ArrayList<>();

        view.menu(title, new ArrayList<>(Arrays.asList("Time (half hour resolution): ")));
        String hour = consoleInput();
        String[] timeSplit = hour.split(":");
        list.add(Integer.valueOf(timeSplit[1]));
        list.add(Integer.valueOf(timeSplit[0]));
        view.menu(title, new ArrayList<>(Arrays.asList("Time: " + hour, "Type day: ")));
        list.add(Integer.parseInt(consoleInput()));

        view.menu(
                title,
                new ArrayList<>(Arrays.asList("Time day: " + hour, "Type day: " + list.get(2), "Type month: ")));
        list.add(Integer.parseInt(consoleInput()));

        view.menu(
                title,
                new ArrayList<>(
                        Arrays.asList("Time day: " + hour, "Type day: " + list.get(2), "Type month: " + list.get(3),
                                "Type year: ")));
        list.add(Integer.parseInt(consoleInput()));
        return list;
    }

}