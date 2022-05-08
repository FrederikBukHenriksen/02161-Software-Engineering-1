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

    

}
