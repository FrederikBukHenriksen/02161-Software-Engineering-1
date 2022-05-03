package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Optional;

import dtu.calculator.ProjectPlanner;

public class commonSteps {

    ProjectPlanner projectPlanner;

    public commonSteps(ProjectPlanner projectPlanner) {
        this.projectPlanner = projectPlanner;
    }

    @Given("an administrator is logged in")
    public void an_administrator_is_logged_in() throws Exception {
        ProjectPlanner.logIn("HUBE", "PW1234");
        assertTrue(ProjectPlanner.administratorLoggedIn());
    }

    @Given("an administrator is not logged in")
    public void an_administrator_is_not_logged_in() {
        assertFalse(ProjectPlanner.administratorLoggedIn());
    }

    @Then("the error message {string} is given")
    public void the_error_message_is_given(String string) {
        assertEquals(string, ErrorMessageHolder.getErrorMessage());
    }

    // ProjectPlanner

    @Given("that there exists a project titled {string} with id {string}")
    public void that_there_exists_a_project_titled_with_id(String title, String id) throws Exception {
        projectPlanner.cucumberCreateProject(title);
        assertTrue(
                projectPlanner.getProjects().stream()
                        .anyMatch(project -> project.getTitle().equalsIgnoreCase(title) && project
                                .getId().equalsIgnoreCase(id)));
    }

    @Given("JUNIT create an employee {string}")
    public void junit_create_an_employee(String initials) {
        projectPlanner.cucumberAddEmployee(initials);
    }

    @Given("JUNIT Add user {string} is to project {string}")
    public void junit_add_user_is_to_project(String userId, String projectId) throws Exception {
        projectPlanner.cucumberAddEmployee(userId);

        User user = projectPlanner.getUser(userId);
        Project project = projectPlanner.getProject(projectId);

        project.cucumberAddUserToProject(user);

    }

    @Given("JUNIT create a project {string}")
    public void junit_create_a_project(String initials) throws Exception {
        projectPlanner.cucumberCreateProject(initials);
    }

    // Project

    @Given("set user {string} as project leader for project {string}")
    public void there_exists_an_employee(String initials, String projectID) {
        try {
            User user = projectPlanner.getUser(initials);
            Project project = projectPlanner.getProject(projectID);
            project.cucumberSetProjectLeader(user);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Given("that the project leader of the project {string} is logged in")
    public void that_the_project_leader_of_the_project_is_logged_in(String projectID) throws Exception {
        try {
            Project project = projectPlanner.getProject(projectID);
            User projectLeader = project.getProjectleader();
            projectPlanner.logIn(projectLeader.getInitials(), projectLeader.getPassword());
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertTrue(projectPlanner.getProject(projectID).isProjectLeaderLoggedIn());
    }

    @Given("that the project leader of the project {string} is not logged in")
    public void that_the_project_leader_of_the_project_is_not_logged_in(String projectID) {
        try {
            Project project = projectPlanner.getProject(projectID);
            assertFalse(project.isProjectLeaderLoggedIn());
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

}
