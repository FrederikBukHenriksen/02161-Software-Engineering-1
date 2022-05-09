package dtu.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class userLoginSteps {
    
    final public CommonSteps commonSteps = new CommonSteps();
    final public ProjectPlanner projectPlanner;

    public userLoginSteps(CommonSteps commonSteps) {
        projectPlanner = commonSteps.projectPlanner;
    }
    
    // Frederik start

    @When("login user {string} with password {string}")
    public void login_user_with_password(String userId, String userPassword) {
        try {
            projectPlanner.logIn(userId, userPassword);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("user {string} is logged in")
    public void user_is_logged_in(String userId) throws Exception {
        User user = projectPlanner.getUser(userId);
        assertTrue(projectPlanner.getLoggedIn().equals(user));
    }

    @Then("user {string} is not logged in")
    public void user_is_not_logged_in(String userId) throws Exception {
        User user = projectPlanner.getUser(userId);
        assertFalse(projectPlanner.getLoggedIn().equals(user));
    }

    @Given("logout")
    public void logout() {
        projectPlanner.logOut();
    }

    @Given("no user is logged in.")
    public void no_user_is_logged_in() {
        assertEquals(projectPlanner.getLoggedIn(), null);
    }
}
