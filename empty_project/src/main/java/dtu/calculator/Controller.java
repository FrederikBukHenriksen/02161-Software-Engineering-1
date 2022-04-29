package dtu.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Stack;

public class Controller {

    ProjectPlanner projectPlanner = new ProjectPlanner();
    View view = new View();

    Scanner scanner = new Scanner(System.in);
    Stack menuStack = new Stack();

    Project selectedProject = null;
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


    public Controller() {
        menuStackPush(logIn);
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

            case createActivity:
                createActivity();
                break;
            case addEmployeeToProject:
                addEmployeeToProject();
                break;
            case removeEmployeeFromProject:
                removeEmployeeFromProject();
                break;
            case selectActivity:
                selectActivity();
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
                    Arrays.asList(createProject, selectProject, addEmployee, removeEmployee, logOut));
        } else if (ProjectPlanner.employeeLoggedIn()) {
            menu = new ArrayList<>(
                    Arrays.asList(registerTime, activityCalendar, logOut));

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
        ArrayList<String> UIListOfProjects = new ArrayList<>();
        for (Project project : ProjectPlanner.getProjects()) {
            UIListOfProjects.add(project.title + ", " + project.id);
        }

        view.menuEnumerate(selectProject, UIListOfProjects);
        try {
            int choice = Integer.parseInt(consoleInputWithBack());

            this.selectedProject = ProjectPlanner.getProjects().get(choice - 1);

            ArrayList<String> menu = new ArrayList<>(
                    Arrays.asList(changeProjectDate,
                            createActivity, selectActivity, addEmployeeToProject,
                            removeEmployeeFromProject, deleteProject));
            view.menuEnumerate(selectProject, menu);
            choice = Integer.parseInt(consoleInputWithBack());
            menuStackPush(menu.get(choice - 1));

        } catch (BackException e) {
        } catch (Exception e) {
            view.error(e);
        }
    }

    public void setProjectLeader() {
        ArrayList<String> UIListOfProjectEmployees = new ArrayList<>();
        for (User employee : selectedProject.getProjectEmployees()) {
            UIListOfProjectEmployees.add(employee.getInitials());
        }

        try {
            view.menuEnumerate(addEmployeeToActivity, UIListOfProjectEmployees);
            int choice = Integer.parseInt(consoleInputWithBack());
            User chosenEmployee = selectedProject.getProjectEmployees().get(choice - 1);
            selectedProject.setProjectLeader(chosenEmployee);
            menuStackPush(selectProject);

        } catch (BackException e) {
        } catch (Exception e) {
            view.error(e);
        }

    }

    public void changeProjectDate() {
        try {
            view.menu(changeProjectDate, new ArrayList<>(Arrays.asList("Type day: ")));
            int day = Integer.parseInt(consoleInputWithBack());
            view.menu(changeProjectDate, new ArrayList<>(Arrays.asList("Type day: " + day, "Type month: ")));
            int month = Integer.parseInt(consoleInputWithBack());
            view.menu(changeProjectDate,
                    new ArrayList<>(Arrays.asList("Type day: " + day, "Type month: " + month, "Type year: ")));
            int year = Integer.parseInt(consoleInputWithBack());
            selectedProject.setStartDate(day, month, year);
            menuStackPush(mainMenu);
        } catch (BackException e) {
        } catch (Exception e) {
            view.error(e);
        }
    }

    public void createActivity() {
        view.menu(createActivity, new ArrayList<>(Arrays.asList("Type activity title: ")));
        try {
            String title = consoleInputWithBack();
            // this.selectedProject.createActivity(title);
            selectedProject.CucumbercreateActivity(title);
            menuStackPush(selectProject); // Go to the project menu.
        } catch (BackException e) {
        } catch (Exception e) {
            view.error(e);
        }
    }

    public void addEmployeeToProject() {
        ArrayList<String> UIListOfEmployees = new ArrayList<>();
        for (User user : ProjectPlanner.getEmployees()) {
            UIListOfEmployees.add(user.initials);
        }

        view.menuEnumerate(addEmployeeToProject, UIListOfEmployees);
        try {
            int choice = Integer.parseInt(consoleInputWithBack());
            User employee = ProjectPlanner.getEmployees().get(choice - 1);
            selectedProject.addEmployeeToProject(employee.getInitials());
            menuStackPush(selectProject);
        } catch (BackException e) {
        } catch (Exception e) {
            view.error(e);
        }
    }

    public void removeEmployeeFromProject() {
        // TODO:Mangler implementering
        menuStackPush(selectProject);

    }

    public void selectActivity() {
        ArrayList<String> UIListOfProjectsActivity = new ArrayList<>();
        for (Activity activity : selectedProject.getActivities()) {
            UIListOfProjectsActivity.add(activity.getTitle());
        }

        view.menuEnumerate(selectActivity, UIListOfProjectsActivity);
        try {

            int choice = Integer.parseInt(consoleInputWithBack());
            this.selectedActivity = selectedProject.getActivities().get(choice - 1);

            ArrayList<String> menu = new ArrayList<>(
                    Arrays.asList(addEmployeeToActivity, removeEmployeeFromActivity,
                            setActivityEstimate, changeActivityStart, changeActivityEnd, removeActivity));
            view.menuEnumerate(selectActivity, menu);
            choice = Integer.parseInt(consoleInputWithBack());
            menuStackPush(menu.get(choice - 1));
        } catch (BackException e) {
        } catch (Exception e) {
            view.error(e);
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
            selectedActivity.addEmployeeToActivity(chosenEmployee);
            menuStackPush(selectActivity);

        } catch (BackException e) {
        } catch (Exception e) {
            view.error(e);
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
            selectedActivity.removeEmployee(chosenEmployee);
            menuStackPush(selectActivity);
        } catch (BackException e) {
        } catch (Exception e) {
            view.error(e);
        }
    }

    public void setActivityEstimate() {
        // TODO: IMplemerting
    }

    public void changeActivityStart() {
        view.menu(changeActivityStart, new ArrayList<>(Arrays.asList("Type acitivity start week: ")));
        try {
            int choice = Integer.parseInt(consoleInputWithBack());
            // TODO: Sæt start tiden
            menuStackPush(selectActivity);
        } catch (BackException e) {
        } catch (Exception e) {
            view.error(e);
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
            view.error(e);
        }
    }

    // Employee-menu

    public void activityCalendar() {
        ArrayList<Activity> employeeActivities = ((Employee) ProjectPlanner.getLoggedIn()).getEmployeeActivities();

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
            GregorianCalendar startTime = new GregorianCalendar(startCal.get(4),
                    startCal.get(
                            3),
                    startCal.get(2), startCal.get(1), startCal.get(0));
            ArrayList<Integer> endCal = setDateWithTime(registerTime + " end time");
            GregorianCalendar endTime = new GregorianCalendar(startCal.get(4),
                    startCal.get(
                            3),
                    startCal.get(2), startCal.get(1), startCal.get(0));

            ((Employee) ProjectPlanner.getLoggedIn()).registerWork(startTime, endTime, chosenActivity);
        } catch (BackException e) {
        } catch (Exception e) {
            view.error(e);
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

        view.menu(title, new ArrayList<>(Arrays.asList("Time (Ex. 8:30 or 13:00): ")));
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