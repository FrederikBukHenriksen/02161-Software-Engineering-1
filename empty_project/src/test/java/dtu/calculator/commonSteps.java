package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CommonSteps{

    final public ProjectPlanner projectPlanner = new ProjectPlanner();

    @Given("the date is year {int} month {int} day {int}")
    public void the_date_is_year_month_day(Integer year, Integer month, Integer date) {
        projectPlanner.dateServer.setDate(year, month, date);
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

    @Given("login user {string}")
    public void user_is_logged_in(String userId) throws Exception {
        User user = projectPlanner.getUser(userId);
        projectPlanner.logIn(user.getInitials(), user.getPassword());
        assertTrue(projectPlanner.getLoggedIn().equals(user));
    }

    // Project


    @Given("set user {string} as project leader for project {string}")
    public void there_exists_an_employee(String initials, String projectID) {
        try {
            User user = projectPlanner.getUser(initials);
            Project project = projectPlanner.getProject(projectID);
            project.setProjectLeader(user);
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

    @Given("the user {string} is logged in")
    public void the_user_is_logged_in(String userId) throws Exception {
        User user = projectPlanner.getUser(userId);
        projectPlanner.logIn(user.getInitials(), user.getPassword());
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
