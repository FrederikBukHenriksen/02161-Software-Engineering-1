package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Test.*;
import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class addEmployeeSteps {

    ProjectPlanner projectPlanner;

    public addEmployeeSteps() {
        projectPlanner = new ProjectPlanner();
    }


    @Given("that the administrator is logged in")
    public void that_the_administrator_is_logged_in() {

        projectPlanner.logIn("HUBE", "PW1234");
        assertEquals("HUBE", projectPlanner.getLoggedIn().getInitials());
        assertTrue(projectPlanner.administratorLoggedIn());

    }

    @Given("the employee {string} is not on the list of employees")
    public void the_employee_is_not_on_the_list_of_employees(String string) {
        assertTrue(projectPlanner.uniqueInitials(string));
    }

        @When("the employee {string} is added to the list of employees")
        @Test(expected = IllegalStateException.class)
        public void the_employee_is_added_to_the_list_of_employees(String string) throws Exception {
            projectPlanner.addEmployee(string);
        }

        @Then("the employee with id {string} is added to the list of employees")
        public void the_employee_with_with_id_is_added_to_the_list_of_employees(String string) {
            assertTrue(
                    projectPlanner.getUsers().stream().anyMatch(user -> user.initials.equalsIgnoreCase(string)));
        }

        // Scenario: Add an employee when the administrator is not logged in

        @Given("that the administrator is not logged in")
        public void that_the_administrator_is_not_logged_in() {
            assertFalse(projectPlanner.administratorLoggedIn());
        }

        @Then("the error message {string} is given")
        public void the_error_message_is_given(String string) {
            assertTrue(true); // TODO: implementering af exception
        }

        // Scenario: Add an employee when it already exists

        @Given("the employee {string} is on the list of employees")
        public void the_employee_is_on_the_list_of_employees(String string) throws Exception {
            projectPlanner.addEmployee(string);
        }

        @Then("the employee {string} only appears once")
        public void the_employee_is_does_not_appear_twice(String string) {
            ArrayList<User> list = new ArrayList<>();
            for (User user : projectPlanner.getUsers()) {
                if (user.getInitials().equalsIgnoreCase(string)) {
                    list.add(user);
                }
            }
            assertTrue(list.size() == 1);
        }

    }
