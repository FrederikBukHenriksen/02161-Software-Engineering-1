package dtu.calculator.controller;

import java.util.ArrayList;

import dtu.calculator.Project;
import dtu.calculator.ProjectPlanner;

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

public class projectMenu {

    final String logIn = "Log in";
    final String logOut = "Log Out";

    final String mainMenu = "Main menu";

    final String createProject = "Create project";

    final String addEmployee = "Add employee";
    final String removeEmployee = "Remove employee";

    final String registerTime = "Register time";
    final String activityCalendar = "My Calendar";

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

    public void selectProject() {
        ArrayList<String> UIListOfProjects = new ArrayList<>();
        for (Project project : projectPlanner.getProjects()) {
            UIListOfProjects.add(project.getTitle() + ", " + project.getId());
        }

        MainController.view.menuEnumerate(selectProject, UIListOfProjects);
        try {
            int choice = Integer.parseInt(MainController.consoleInputWithBack());

            MainController.selectedProject = projectPlanner.getProjects().get(choice - 1);

            if (projectPlanner.administratorLoggedIn()) {

                ArrayList<String> menu = new ArrayList<>(
                        Arrays.asList(getProjectInfo, setProjectLeader, deleteProject));
                MainController.view.menuEnumerate(selectProject, menu);
                choice = Integer.parseInt(MainController.consoleInputWithBack());
                MainController.menuStackPush(menu.get(choice - 1));

            } else if (MainController.selectedProject.projectLeaderLoggedIn()) {

                ArrayList<String> menu = new ArrayList<>(
                        Arrays.asList(getProjectInfo, changeProjectDate, addEmployeeToProject, createActivity,
                                selectActivity,
                                removeEmployeeFromProject));
                MainController.view.menuEnumerate(selectProject, menu);
                choice = Integer.parseInt(MainController.consoleInputWithBack());
                MainController.menuStackPush(menu.get(choice - 1));

            } else if (projectPlanner.employeeLoggedIn()) {

                ArrayList<String> menu = new ArrayList<>(
                        Arrays.asList(getProjectInfo));
                MainController.view.menuEnumerate(selectProject, menu);
                choice = Integer.parseInt(MainController.consoleInputWithBack());
                MainController.menuStackPush(menu.get(choice - 1));

            } else {
                MainController.menuStackPush(selectProject);
            }

        } catch (

        BackException e) {
        } catch (Exception e) {
            MainController.view.error(e);
        }
    }

    public void setProjectLeader() {
        ArrayList<String> UIListOfEmployees = new ArrayList<>();
        for (User employee : projectPlanner.getEmployees()) {
            UIListOfEmployees.add(employee.getInitials());
        }

        try {
            MainController.view.menuEnumerate(setProjectLeader, UIListOfEmployees);
            int choice = Integer.parseInt(MainController.consoleInputWithBack());
            User chosenEmployee = projectPlanner.getEmployees().get(choice - 1);
            MainController.selectedProject.setProjectLeader(chosenEmployee);
            MainController.menuStackPush(selectProject);

        } catch (BackException e) {
        } catch (Exception e) {
            MainController.view.error(e);
        }

    }

    public void changeProjectDate() {
        try {
            MainController.view.menu(changeProjectDate, new ArrayList<>(Arrays.asList("Type day: ")));
            int day = Integer.parseInt(MainController.consoleInputWithBack());
            MainController.view.menu(changeProjectDate,
                    new ArrayList<>(Arrays.asList("Type day: " + day, "Type month: ")));
            int month = Integer.parseInt(MainController.consoleInputWithBack());
            MainController.view.menu(changeProjectDate,
                    new ArrayList<>(Arrays.asList("Type day: " + day, "Type month: " + month, "Type year: ")));
            int year = Integer.parseInt(MainController.consoleInputWithBack());
            MainController.selectedProject.setStartDate(day, month, year);
            MainController.menuStackPush(mainMenu);
        } catch (BackException e) {
        } catch (Exception e) {
            MainController.view.error(e);
        }
    }

    public void createActivity() {
        MainController.view.menu(createActivity, new ArrayList<>(Arrays.asList("Type activity title: ")));
        try {
            String title = MainController.consoleInputWithBack();
            // this.selectedProject.createActivity(title);
            MainController.selectedProject.CucumbercreateActivity(title);
            MainController.menuStackPush(selectProject); // Go to the project menu.
        } catch (BackException e) {
        } catch (Exception e) {
            MainController.view.error(e);
        }
    }

    public void addEmployeeToProject() {
        ArrayList<String> UIListOfEmployees = new ArrayList<>();
        for (User user : projectPlanner.getEmployees()) {
            if (!MainController.selectedProject.getProjectEmployees().contains(user)) {
                UIListOfEmployees.add(user.getInitials());
            }
        }

        MainController.view.menuEnumerate(addEmployeeToProject, UIListOfEmployees);
        try {
            int choice = Integer.parseInt(MainController.consoleInputWithBack());
            User employee = projectPlanner.getEmployees().get(choice - 1);
            MainController.selectedProject.addEmployeeToProject(employee);
            MainController.menuStackPush(selectProject);
        } catch (BackException e) {
        } catch (Exception e) {
            MainController.view.error(e);
        }
    }

    public void removeEmployeeFromProject() {
        ArrayList<String> UIListOfEmployees = new ArrayList<>();
        for (User user : MainController.selectedProject.getProjectEmployees()) {
            UIListOfEmployees.add(user.getInitials());
        }

        MainController.view.menuEnumerate(removeEmployeeFromProject, UIListOfEmployees);
        try {
            int choice = Integer.parseInt(MainController.consoleInputWithBack());
            User employee = projectPlanner.getEmployees().get(choice - 1);
            MainController.selectedProject.removeEmployeeFromProject(employee);
            MainController.menuStackPush(selectProject);
        } catch (BackException e) {
        } catch (Exception e) {
            MainController.view.error(e);
        }
    }

    public void getProjectInfo() {
        ArrayList<String> UIProjectInfo = new ArrayList<>();

        UIProjectInfo.add("Project title: " + MainController.selectedProject.getTitle());
        UIProjectInfo.add("Project start date: " + MainController.selectedProject.getStartDate());
        // UIProjectInfo.add("Project end date: " + project.getEndDate());
        UIProjectInfo.add("Project leader: " + MainController.selectedProject.getProjectleader().getInitials());
        UIProjectInfo.add("Project employees: ");
        for (User employee : MainController.selectedProject.getProjectEmployees()) {
            UIProjectInfo.add(employee.getInitials());
        }
        UIProjectInfo.add("Project activities: ");
        for (Activity activity : MainController.selectedProject.getActivities()) {
            UIProjectInfo.add(activity.getTitle());
        }
        MainController.view.menu(getProjectInfo, UIProjectInfo);

        try {
            int choice = Integer.parseInt(MainController.consoleInputWithBack());
            MainController.menuStackPush(selectProject);
        } catch (BackException e) {
        } catch (Exception e) {
            MainController.view.error(e);
        }
    }

}

