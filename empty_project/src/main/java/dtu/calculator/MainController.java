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
    final String getProjectInfo = "Get project info";
    final String registerLeave = "Register leave";

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
            case addEmployeeToActivity:
                addEmployeeToActivity();
                break;
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
            case registerLeave:
                registerLeave();
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
        if (input.equalsIgnoreCase("back")) {
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
        // if (!menuStack.empty()) {
        // menuStack.pop();
        // }
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
                    Arrays.asList(createProject, selectProject, addEmployee, removeEmployee, registerLeave, logOut));
        } else if (projectPlanner.isEmployeeLoggedIn()) {
            menu = new ArrayList<>(
                    Arrays.asList(selectProject, registerTime, activityCalendar, registerLeave, logOut));

        }

        view.menuEnumerate(mainMenu, menu);
        

        try {
            String choice = consoleInput();
        String menuSelect = menu.get(Integer.parseInt(choice) - 1);
        menuStackPush(menuSelect);
        } catch (Exception e) {
            e = new Exception("Your input is not Valid");
            handleException(e);
        }

    }

    public void createProject() {
        view.menu(createProject, new ArrayList<>(Arrays.asList("Project title: ")));
        try {
            String input = consoleInputWithBack();

            projectPlanner.createProject(input);
            menuStackPop();
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

            menuStackPop();
        } catch (BackException e) {
        } catch (Exception e) {
        }
    }

    public void addEmployee() {
        view.menu(addEmployee, new ArrayList<>(Arrays.asList("Employee initials: ")));
        try {
            String input = consoleInputWithBack();
            projectPlanner.createEmployee(input);
            menuStackPop();
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

            menuStackPop();
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
            menuStackPop();

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
            menuStackPop();
        } catch (BackException e) {
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void setActivityEstimate() {
        view.menu(setActivityEstimate, new ArrayList<>(Arrays.asList("Type estimate: ")));
        try {
            double estimate = Double.parseDouble(consoleInputWithBack());
            selectedActivity.setActivityEstimate(estimate);
            menuStackPop();
        } catch (BackException e) {
        } catch (Exception e) {
            handleException(e);

        }
    }

    public void changeActivityStart() {
        view.menu(changeActivityStart, new ArrayList<>(Arrays.asList("Type acitivity start year: ")));
        try {
            int choice_year = Integer.parseInt(consoleInputWithBack());
            view.menu(changeActivityStart,
                    new ArrayList<>(Arrays.asList("Type acitivity start year: " + String.valueOf(choice_year),
                            "type activity start week: ")));
            int choice_week = Integer.parseInt(consoleInputWithBack());
            selectedActivity.setStartDate(choice_year, choice_week);
            menuStackPop();
        } catch (BackException e) {
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void changeActivityEnd() {
        view.menu(changeActivityStart, new ArrayList<>(Arrays.asList("Type acitivity end year: ")));
        try {
            int choice_year = Integer.parseInt(consoleInputWithBack());
            view.menu(changeActivityStart,
                    new ArrayList<>(Arrays.asList("Type acitivity end year: " + String.valueOf(choice_year),
                            "type activity end week: ")));
            int choice_week = Integer.parseInt(consoleInputWithBack());
            selectedActivity.setEndDate(choice_year, choice_week);
            menuStackPop();
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
            String endDate = "N/A";
            try {
                endDate = activity.getEndDate().getDateString();
            } catch (Exception e) {
                endDate = "N/A";
            }

            String startDate = "N/A";
            try {
                startDate = activity.getStartDate().getDateString();
            } catch (Exception e) {
                startDate = "N/A";
            }

            UIlistOfActivities.add(
                    activity.getTitle() + ", Start: " + startDate + ", end: " + endDate + ", estimate: "
                            + String.valueOf(activity.getActivityEstimate()));
        }

        try {
            ArrayList<Leave> employeeLeave = ((Employee) projectPlanner.getLoggedIn()).getLeaveAll();
            for (Leave leave : employeeLeave) {
                String endDate = "N/A";
                try {
                    endDate = leave.getEndTime().getDateString();
                } catch (Exception e) {
                    endDate = "N/A";
                }

                String startDate = "N/A";
                try {
                    startDate = leave.getStartTime().getDateString();
                } catch (Exception e) {
                    startDate = "N/A";
                }

                UIlistOfActivities.add(
                        leave.getLeaveTitle() + ", Start: " + startDate + ", end: " + endDate);
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

        view.menuEnumerate(activityCalendar, UIlistOfActivities);
        String input = consoleInput();
        menuStackPop();
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

            User user = (User) projectPlanner.getLoggedIn();

            ArrayList<Integer> startCal = setDateWithTime(registerTime + " start time");
            CustomCalendar startTime = new CustomCalendar(startCal.get(4), startCal.get(3), startCal.get(2),
                    startCal.get(1), startCal.get(0));

            ArrayList<Integer> endCal = setDateWithTime(registerTime + " end time");
            CustomCalendar endTime = new CustomCalendar(endCal.get(4), endCal.get(3), endCal.get(2), endCal.get(1),
                    endCal.get(0));
            user.registerWork(startTime, endTime, chosenActivity);

            ((Employee) projectPlanner.getLoggedIn()).registerWork(startTime, endTime, chosenActivity);
        } catch (BackException e) {
        } catch (Exception e) {
            handleException(e);
        }

    }

    public void removeActivity() {
        ArrayList<String> UIListOfActivities = new ArrayList<>();
        try {
            for (Activity activity : projectPlanner.getProject(selectProject).getActivities()) {
                UIListOfActivities.add(activity.getTitle());
            }
        } catch (Exception e) {
            view.error(e);
        }

        view.menuEnumerate(deleteProject, UIListOfActivities);

        try {
            selectedProject.deleteActivity(selectedActivity);
            menuStackPop();
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

            view.menu(title, new ArrayList<>(Arrays.asList("Time (half hour resolution(hour:00 or hour:30)): ")));
        try {
            
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
        } catch (Exception e) {
            e = new Exception("your input is invalid");
            handleException(e);
        }
        return null;
    }

    public void registerLeave() {
        view.menu(registerLeave, new ArrayList<>(Arrays.asList("Type leave start year: ")));
        try {
            int choice_year_start = Integer.parseInt(consoleInputWithBack());
            view.menu(registerLeave,
                    new ArrayList<>(Arrays.asList("Type leave start year: " + String.valueOf(choice_year_start),
                            "Type activity start Month: ")));
            int choice_month_start = Integer.parseInt(consoleInputWithBack());

            view.menu(registerLeave,
                    new ArrayList<>(Arrays.asList("Type leave start year: " + String.valueOf(choice_year_start),
                            "Type leave start Month: " + String.valueOf(choice_month_start), "Type leave start day: ")));
            int choice_day_start = Integer.parseInt(consoleInputWithBack());
            

            view.menu(registerLeave, new ArrayList<>(Arrays.asList("Type leave end year: ")));
            int choice_year_end = Integer.parseInt(consoleInputWithBack());
            view.menu(registerLeave,
                    new ArrayList<>(Arrays.asList("Type leave end year: " + String.valueOf(choice_year_end),
                            "Type leave end Month: ")));
            int choice_month_end = Integer.parseInt(consoleInputWithBack());

            view.menu(registerLeave,
                    new ArrayList<>(Arrays.asList("Type leave end year: " + String.valueOf(choice_year_end),
                            "Type leave end Month: " + String.valueOf(choice_month_end), "Type leave end day: ")));
            int choice_day_end = Integer.parseInt(consoleInputWithBack());


            view.menu(registerLeave, new ArrayList<>(Arrays.asList("Type leave title: ")));
            String titleString = consoleInputWithBack();
            
            projectPlanner.getLoggedIn().createLeave(new CustomCalendar(choice_year_start, choice_month_start, choice_day_start), new CustomCalendar(choice_year_end, choice_month_end, choice_day_end),titleString);
            
            menuStackPop();
        } catch (BackException e) {
        } catch (Exception e) {
            handleException(e);
        }

    }
    

}