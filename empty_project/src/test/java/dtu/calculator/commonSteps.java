package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Optional;

import dtu.calculator.ProjectPlanner;

public class commonSteps {

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
}
