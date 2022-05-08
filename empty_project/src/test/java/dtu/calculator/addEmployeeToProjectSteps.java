package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class addEmployeeToProjectSteps {

    final public CommonSteps commonSteps = new CommonSteps();
    final public ProjectPlanner projectPlanner;

    public addEmployeeToProjectSteps(CommonSteps commonSteps) {
        projectPlanner = commonSteps.projectPlanner;
    }

    // FREDERIK START
    @Given("add user {string} to project {string}")
    public void add_user_to_project(String userId, String projectId) throws Exception {
        try {
            Project project = projectPlanner.getProject(projectId);
            User user = projectPlanner.getUser(userId);
            project.addUserToProject(user);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the user {string} is in the project {string}")
    public void the_user_is_assigned_to_the_project(String userInitials, String projectID) throws Exception {
        Project project = projectPlanner.getProject(projectID);
        User user = projectPlanner.getUser(userInitials);
        assertTrue(project.getProjectEmployees().contains(user));
    }

    @Given("the user {string} is not in the project {string}")
    public void the_user_is_not_in_the_project(String userInitials, String projectID) throws Exception {
        Project project = projectPlanner.getProject(projectID);
        User user = projectPlanner.getUser(userInitials);
        assertFalse(project.getProjectEmployees().contains(user));
    }

    @Then("the user {string} is only once in the project {string}")
    public void the_user_is_only_once_in_the_project(String userID, String projectID) throws Exception {
        Project project = projectPlanner.getProject(projectID);
        long count = project.getProjectEmployees().stream().filter(user -> user.getInitials().equalsIgnoreCase(userID))
                .count();
        assertEquals(count, 1);

    }



}
