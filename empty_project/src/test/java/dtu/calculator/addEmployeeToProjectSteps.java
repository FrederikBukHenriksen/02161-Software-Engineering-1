package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class addEmployeeToProjectSteps {

    ProjectPlanner projectPlanner;

     @io.cucumber.java.Before
    public void Before() {
        Memory.reset();
    }

    public addEmployeeToProjectSteps() {
        projectPlanner = new ProjectPlanner();
    }



    @Given("that the project leader with id {string} for the project {string} is logged in")
    public void that_the_project_leader_with_id_for_the_project_is_logged_in(String projectLeaderInitials,
            String projectID) throws Exception {
        
        projectPlanner.cucumberAddEmployee(projectLeaderInitials);
        User projectLeader = null;
        for (User projectLeader_ : ProjectPlanner.getUsers()) {
            if (projectLeader_.getInitials().equalsIgnoreCase(projectLeaderInitials)) {
                projectLeader = projectLeader_;
            }
        }
        Project project = ProjectPlanner.getProject(projectID);

        project.setProjectLeader(projectLeader);

        ProjectPlanner.logIn(projectLeaderInitials, "01234");
        assertTrue(project.isProjectLeaderLoggedIn());
        
    }

    @Given("project leader for the project {string} has chosen an employee with id {string}")
public void project_leader_for_the_project_has_chosen_an_employee_with_id(String projectID, String employeeID) {
        projectPlanner.cucumberAddEmployee(employeeID);
        
    }

    @Given("the employee {string} isn't in the project {string}")
public void the_employee_with_id_isn_t_in_the_project_with_id(String employeeID, String projectID) throws Exception {
        Project project = ProjectPlanner.getProject(projectID);
        User employee = null;
        for (User employee_ : ProjectPlanner.getUsers()) {
            if (employee_.getInitials().equalsIgnoreCase(employeeID)) {
                employee = employee_;
            }
        }
        boolean isInProject;
        if (!project.getProjectEmployees().contains(employee)) {
            isInProject = true;
        } else {
            throw new Exception("Employee is already in project");
        }
        assertTrue(isInProject);
    }

    @When("the user {string} is added to the project {string}")
    public void the_user_is_added_to_the_project(String userID, String projectID) throws Exception {
        Project project = ProjectPlanner.getProject(projectID);
        try {
            User user = ProjectPlanner.getUser(userID);
            project.addUserToProject(user);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the user {string} is only once in the project {string}")
    public void the_user_is_only_once_in_the_project(String userID, String projectID) throws Exception {
        Project project = ProjectPlanner.getProject(projectID);
        long count = project.getProjectEmployees().stream().filter(user -> user.getInitials().equalsIgnoreCase(userID))
                .count();
        assertEquals(count, 1);

    }

    @Then("the user {string} is in the project {string}")
    public void the_user_is_assigned_to_the_project(String userInitials, String projectID) throws Exception {
        Project project = ProjectPlanner.getProject(projectID);
        User user = ProjectPlanner.getUser(userInitials);
        assertTrue(project.getProjectEmployees().contains(user));
    }

    @Then("the employee with id {string} is not added to the project with id {string}")
    public void the_employee_with_id_is_not_added_to_the_project_with_id(String employeeID, String projectId)
            throws Exception {
        Project project = ProjectPlanner.getProject(projectId);
        assertFalse(project.getProjectEmployees().stream()
                .anyMatch(employee -> employee.getInitials().equalsIgnoreCase(employeeID)));
    }

    @Given("the project leader for project {string} is {string}")
    public void the_project_leader_for_project_is(String projectID, String userInitials) {
        try {
            Project project = projectPlanner.getProject(projectID);
            User user = projectPlanner.getUser(userInitials);
            project.cucumberSetProjectLeader(user);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Given("the user {string} is not in the project {string}")
    public void the_user_is_not_in_the_project(String userInitials, String projectID) throws Exception {
        Project project = projectPlanner.getProject(projectID);
        User user = projectPlanner.getUser(userInitials);
        assertFalse(project.getProjectEmployees().contains(user));
    }

}
