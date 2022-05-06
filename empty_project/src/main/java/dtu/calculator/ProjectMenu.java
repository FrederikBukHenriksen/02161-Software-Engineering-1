package dtu.calculator;

import java.util.ArrayList;

import dtu.calculator.Project;
import dtu.calculator.ProjectPlanner;

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
import dtu.calculator.MainController;
import dtu.calculator.MainController;
import dtu.calculator.Project;
import dtu.calculator.ProjectPlanner;
import dtu.calculator.User;
import dtu.calculator.View;

public class ProjectMenu {

    ProjectPlanner projectPlanner;
    MainController mainController;

    final String logIn = "Log in";
    final String logOut = "Log Out";

    final static String mainMenu = "Main menu";

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

    public ProjectMenu(ProjectPlanner projectPlanner, MainController mainController) {
        this.projectPlanner = projectPlanner;
        this.mainController = mainController;
    }

    public void selectProject() {
        ArrayList<String> UIListOfProjects = new ArrayList<>();
        for (Project project : projectPlanner.getProjects()) {
            UIListOfProjects.add(project.getTitle() + ", " + project.getId());
        }

        mainController.view.menuEnumerate(selectProject, UIListOfProjects);
        try {
            int choice = Integer.parseInt(mainController.consoleInputWithBack());

            mainController.selectedProject = projectPlanner.getProjects().get(choice - 1);

            ArrayList<String> menu = new ArrayList<>(
                    Arrays.asList( // changeProjectDate, createActivity, selectActivity,
                            addEmployeeToProject,
                            removeEmployeeFromProject, setProjectLeader, deleteProject));
            mainController.view.menuEnumerate(selectProject, menu);
            choice = Integer.parseInt(mainController.consoleInputWithBack());
            mainController.menuStackPush(menu.get(choice - 1));

        } catch (BackException e) {
        } catch (Exception e) {
            mainController.view.error(e);
        }
    }

    public void setProjectLeader() {
        ArrayList<String> UIListOfProjectEmployees = new ArrayList<>();
        for (User employee : mainController.selectedProject.getProjectEmployees()) {
            UIListOfProjectEmployees.add(employee.getInitials());
        }

        try {
            mainController.view.menuEnumerate(setProjectLeader, UIListOfProjectEmployees);
            int choice = Integer.parseInt(mainController.consoleInputWithBack());
            User chosenEmployee = mainController.selectedProject.getProjectEmployees().get(choice - 1);
            mainController.selectedProject.setProjectLeader(chosenEmployee);
            mainController.menuStackPush(selectProject);

        } catch (BackException e) {
        } catch (Exception e) {
            mainController.view.error(e);
        }

    }

    public void changeProjectDate() {
        try {
            mainController.view.menu(changeProjectDate, new ArrayList<>(Arrays.asList("Type day: ")));
            int day = Integer.parseInt(mainController.consoleInputWithBack());
            mainController.view.menu(changeProjectDate,
                    new ArrayList<>(Arrays.asList("Type day: " + day, "Type month: ")));
            int month = Integer.parseInt(mainController.consoleInputWithBack());
            mainController.view.menu(changeProjectDate,
                    new ArrayList<>(Arrays.asList("Type day: " + day, "Type month: " + month, "Type year: ")));
            int year = Integer.parseInt(mainController.consoleInputWithBack());
            mainController.selectedProject.setStartDate(day, month, year);
            mainController.menuStackPush(mainMenu);
        } catch (BackException e) {
        } catch (Exception e) {
            mainController.view.error(e);
        }
    }

    public void createActivity() {
        mainController.view.menu(createActivity, new ArrayList<>(Arrays.asList("Type activity title: ")));
        try {
            String title = mainController.consoleInputWithBack();
            // this.selectedProject.createActivity(title);
            mainController.selectedProject.CucumberCreateActivity(title);
            mainController.menuStackPush(selectProject); // Go to the project menu.
        } catch (BackException e) {
        } catch (Exception e) {
            mainController.view.error(e);
        }
    }

    public void addEmployeeToProject() {
        ArrayList<String> UIListOfEmployees = new ArrayList<>();
        for (User user : projectPlanner.getEmployees()) {
            if (!mainController.selectedProject.getProjectEmployees().contains(user)) {
                UIListOfEmployees.add(user.getInitials());
            }

        }

        mainController.view.menuEnumerate(addEmployeeToProject, UIListOfEmployees);
        try {
            int choice = Integer.parseInt(mainController.consoleInputWithBack());
            User employee = projectPlanner.getEmployees().get(choice - 1);
            mainController.selectedProject.addUserToProject(employee); // Muhammed retter dette.
            mainController.menuStackPush(selectProject);
        } catch (BackException e) {
        } catch (Exception e) {
            mainController.view.error(e);
        }
    }

    public void removeEmployeeFromProject() {
        // TODO:Mangler implementering
        mainController.menuStackPush(selectProject);

    }

}