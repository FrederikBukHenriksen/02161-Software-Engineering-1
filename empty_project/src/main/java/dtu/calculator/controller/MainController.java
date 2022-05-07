package dtu.calculator.controller;

import dtu.calculator.controller.projectMenu;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Stack;

import dtu.calculator.Activity;
import dtu.calculator.BackException;
import dtu.calculator.Employee;
import dtu.calculator.Project;
import dtu.calculator.ProjectPlanner;
import dtu.calculator.User;
import dtu.calculator.View;
import dtu.calculator.Work;

public class MainController {

    public static ProjectPlanner projectPlanner = new ProjectPlanner();
    static View view = new View();

    static Scanner scanner = new Scanner(System.in);
    static Stack menuStack = new Stack();

    public static Project selectedProject = null;
    static Activity selectedActivity = null;

    final static String logIn = "Log in";
    final static String logOut = "Log Out";

    final static String mainMenu = "Main menu";

    final static String createProject = "Create project";

    final static String addEmployee = "Add employee";
    final static String removeEmployee = "Remove employee";

    final static String registerTime = "Register time";
    final static String activityCalendar = "My Calendar";

    // Projektmenu
    final static String selectProject = "Select project";

    final static String deleteProject = "Delete project";

    final static String setProjectLeader = "Set project leader";

    final static String changeProjectDate = "Change project date";

    final static String createActivity = "Create Activity";

    final static String addEmployeeToProject = "Add employee to project";
    final static String removeEmployeeFromProject = "Remove employee from project";

    // Activitymenu
    final static String selectActivity = "Select activity";

    final static String removeActivity = "Remove Activity";

    final static String addEmployeeToActivity = "Add employee to activity";
    final static String removeEmployeeFromActivity = "Remove employee from activity";

    final static String setActivityEstimate = "Set activity estimate";
    final static String changeActivityStart = "Change activity start";
    final static String changeActivityEnd = "Change activity end";
    final static String getProjectInfo = "Get project info";

    public MainController() {
        menuStackPush(logIn);
    }

    public void show() {
        while (!menuStack.empty()) { // Essentially runs as long as the program.
            menuStackDecode(menuStackPeek());
        }
    }

    public static void menuStackDecode(String menu) {
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
            case addEmployeeToActivity:
                addEmployeeToActivity();
            case changeActivityStart:
                changeActivityStart();
                break;
            case changeActivityEnd:
                changeActivityEnd();
                break;
            case removeEmployeeFromActivity:
                removeEmployeeFromActivity();
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
            case removeActivity:
                removeActivity();
                break;
            case selectActivity:
                selectActivity();
                break;
            case setActivityEstimate:
                setActivityEstimate();
                break;
            case setProjectLeader:
                projectMenu.setProjectLeader();
                break;
            case activityCalendar:
                activityCalendar();
                break;
            case registerTime:
                registerTime();
                break;
            case getProjectInfo:
                projectMenu.getProjectInfo();
                break;
            default:
                break;
        }

    }

    public static String consoleInput() {
        String input = scanner.next();
        return input;
    }

    public static String consoleInputWithBack() throws BackException {
        String input = "";
        input = scanner.next();
        if (input.equals("back")) {
            menuStackPop();
            menuStackDecode(menuStackPeek());
            throw new BackException();
        }
        return input;
    }

    public static void handleException(Exception exception) {
        view.error(exception);
        pressEnterToContinue();
    }

    public static void pressEnterToContinue() {
        boolean flag = false;
        while (flag == false) {
            Scanner enterScanner = new Scanner(System.in);
            String input = enterScanner.nextLine();
            if (input.equals("")) {
                flag = true;
            }
        }
    }

    public static void menuStackPush(String menu) {
        menuStack.push(menu);
    }

    public static String menuStackPop() {
        return menuStack.pop().toString();
    }

    public static String menuStackPeek() {
        return menuStack.peek().toString();
    }

    public static void menuStackClear() {
        menuStack.clear();
    }

    public static void logIn() {
        view.menu(logIn, new ArrayList<>(Arrays.asList("Initials: ")));
        String initials = consoleInput();
        view.menu(logIn, new ArrayList<>(Arrays.asList("Initials: " + initials, "Password: ")));
        String password = consoleInput();
        try {
            ProjectPlanner.logIn(initials, password);
            menuStackPush(mainMenu);
        } catch (Exception e) {
            handleException(e);
            menuStackPush(logIn);
        }
    }

    public static void mainMenu() {

        view.clearScreen();
        ArrayList<String> menu = new ArrayList<>();
        if (ProjectPlanner.administratorLoggedIn()) {
            menu = new ArrayList<>(
                    Arrays.asList(createProject, selectProject, addEmployee, removeEmployee, logOut));
        } else if (ProjectPlanner.employeeLoggedIn()) {
            menu = new ArrayList<>(
                    Arrays.asList(selectProject, registerTime, activityCalendar, logOut));

        }

        view.menuEnumerate(mainMenu, menu);
        String choice = consoleInput();
        String menuSelect = menu.get(Integer.parseInt(choice) - 1);
        menuStackPush(menuSelect);

    }

    public static void createProject() {
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

    public static void deleteProject() {

        ArrayList<String> UIListOfProjects = new ArrayList<>();
        for (Project project : ProjectPlanner.getProjects()) {
            UIListOfProjects.add(project.getTitle() + ", " + project.getId());
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

    public static void addEmployee() {
        view.menu(addEmployee, new ArrayList<>(Arrays.asList("Employee initials: ")));
        try {
            String input = consoleInputWithBack();
            projectPlanner.addEmployee(input);
            menuStackPush(mainMenu);
        } catch (BackException e) {
        } catch (Exception e) {
            handleException(e);
        }
    }

    public static void removeEmployee() {

        ArrayList<String> UIListOfEmployees = new ArrayList<>();
        for (User user : ProjectPlanner.getEmployees()) {
            UIListOfEmployees.add(user.getInitials());
        }
        view.menuEnumerate(removeEmployee, UIListOfEmployees);
        try {
            int choice = Integer.parseInt(consoleInputWithBack());

            User chosenEmployee = ProjectPlanner.getEmployees().get(choice - 1);
            projectPlanner.removeEmployee(chosenEmployee);

            menuStackPush(mainMenu);
        } catch (BackException e) {
        } catch (Exception e) {
            handleException(e);
        }
    }

    // project start:

    // project end;
    public static void selectActivity() {
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

    public static void addEmployeeToActivity() {
        ArrayList<String> UIListOfProjectEmployees = new ArrayList<>();
        for (User employee : selectedProject.getProjectEmployees()) {
            UIListOfProjectEmployees.add(employee.getInitials());
        }

        try {
            view.menuEnumerate(addEmployeeToActivity, UIListOfProjectEmployees);
            int choice = Integer.parseInt(consoleInputWithBack());
            User chosenEmployee = selectedProject.getProjectEmployees().get(choice - 1);
            selectedActivity.addEmployeeToActivity(chosenEmployee);
            menuStackPush(selectActivity);

        } catch (BackException e) {
        } catch (Exception e) {
            handleException(e);
        }

    }

    public static void removeEmployeeFromActivity() {
        ArrayList<String> UIListOfActivityEmployees = new ArrayList<>();
        for (User employee : selectedActivity.getEmployees()) {
            UIListOfActivityEmployees.add(employee.getInitials());
        }
        try {
            view.menuEnumerate(removeEmployeeFromActivity, UIListOfActivityEmployees);
            int choice = Integer.parseInt(consoleInputWithBack());

            User chosenEmployee = selectedActivity.getEmployees().get(choice - 1);
            selectedActivity.removeEmployee(chosenEmployee);
            menuStackPush(selectActivity);
        } catch (BackException e) {
        } catch (Exception e) {
            handleException(e);
        }
    }

    public static void setActivityEstimate() {
        view.menu(setActivityEstimate, new ArrayList<>(Arrays.asList("Type estimate: ")));
        try {
            double estimate = Double.parseDouble(consoleInputWithBack());
            selectedActivity.setActivityEstimate(estimate);
            menuStackPush(selectActivity);
        } catch (BackException e) {
        } catch (Exception e) {
            handleException(e);

        }
    }

    public static void changeActivityStart() {
        view.menu(changeActivityStart, new ArrayList<>(Arrays.asList("Type acitivity start week: ")));
        try {
            int choice_week = Integer.parseInt(consoleInputWithBack());
            view.menu(changeActivityStart,
                    new ArrayList<>(Arrays.asList("Type acitivity start Year: ", "type activity start week: ")));
            int choice_year = Integer.parseInt(consoleInputWithBack());
            selectedActivity.setStartDate(choice_year, choice_week);
            menuStackPush(selectActivity);
        } catch (BackException e) {
        } catch (Exception e) {
            handleException(e);
        }
    }

    public static void changeActivityEnd() {
        view.menu(changeActivityStart, new ArrayList<>(Arrays.asList("Type acitivity end week: ")));
        try {
            int choice_year = Integer.parseInt(consoleInputWithBack());
            view.menu(changeActivityStart,
                    new ArrayList<>(Arrays.asList("Type acitivity start Year: ", "type activity start week: ")));
            int choice_week = Integer.parseInt(consoleInputWithBack());
            selectedActivity.setEndDate(choice_year, choice_week);
            menuStackPush(selectActivity);
        } catch (BackException e) {
        } catch (Exception e) {
            handleException(e);
        }
    }

    // Employee-menu

    public static void activityCalendar() {
        ArrayList<Activity> employeeActivities = ((Employee) ProjectPlanner.getLoggedIn()).getEmployeeActivities();

        ArrayList<String> UIlistOfActivities = new ArrayList<>();
        for (Activity activity : employeeActivities) {
            UIlistOfActivities.add(
                    activity.getTitle() + ", Start: " + activity.getStartDate() + ", end: " + activity.getEndDate());
        }

        view.menuEnumerate(activityCalendar, UIlistOfActivities);
        String input = consoleInput();
        menuStackPush(mainMenu);
    }

    public static void registerTime() {
        ArrayList menu = new ArrayList<>(
                Arrays.asList("Activities you are assigned to", "Projects you are a part of", "All projects"));
        view.menuEnumerate(registerTime, menu);
        try {
            int choice = Integer.parseInt(consoleInputWithBack());

            // Find the selectable activities according to the choice.
            ArrayList<Activity> listOfActivities = new ArrayList<>();
            switch (choice) {
                case 1:
                    listOfActivities = ((Employee) ProjectPlanner.getLoggedIn()).getEmployeeActivities();

                    break;
                case 2:
                    for (Project project : ProjectPlanner.getProjects()) {
                        if (project.getProjectEmployees().contains(ProjectPlanner.loggedIn)) {
                            for (Activity activity : project.getActivities()) {
                                listOfActivities.add(activity);
                            }
                        }
                    }
                    break;
                case 3:
                    for (Project project : ProjectPlanner.getProjects()) {
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

            ((Employee) ProjectPlanner.getLoggedIn()).registerWork(startTime, endTime, chosenActivity);
        } catch (BackException e) {
        } catch (Exception e) {
            handleException(e);
        }

    }

    public static void removeActivity() {
        // ArrayList<String> UIListOfActivities = new ArrayList<>();
        // for (Activity activity :
        // ProjectPlanner.getProject(selectProject).getActivities()) {
        // UIListOfActivities.add(activity.getTitle());
        // }

        // view.menuEnumerate(deleteProject, UIListOfActivities);

        try {
            MainController.selectedProject.removeActivity(MainController.selectedActivity);
            MainController.menuStackPush(selectProject);
        } catch (Exception e) {
            MainController.view.error(e);
        }

    }

    public static void logOut() {
        ProjectPlanner.logOut();
        menuStackClear();
        menuStackPush(logIn);
    }

    // Help-functions
    public static ArrayList<Integer> setDateWithTime(String title) {
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