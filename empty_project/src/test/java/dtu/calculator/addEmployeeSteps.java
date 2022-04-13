package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;


public class addEmployeeSteps {

    ProjectPlanner projectPlanner;

    public addEmployeeSteps(ProjectPlanner projectplanner) {
        this.projectPlanner = projectplanner;
    }

    @Given("the employee {string} is not on the list of employees")
    public void the_employee_is_not_on_the_list_of_employees(String string) {
        assertFalse(ProjectPlanner.getUsers().stream().anyMatch(user -> user.initials.equalsIgnoreCase(string)));
    }

    @When("the employee {string} is added to the list of employees")
    public void the_employee_is_added_to_the_list_of_employees(String initials) {
        try {
            projectPlanner.addEmployee(initials);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the employee {string} is on the list")
        public void the_employee_with_with_id_is_added_to_the_list_of_employees(String string) {
            assertTrue(
                    ProjectPlanner.getUsers().stream().anyMatch(user -> user.initials.equalsIgnoreCase(string)));
        }

        // Scenario: Add an employee when the administrator is not logged in

        // Scenario: Add an employee when it already exists

        @Given("the employee {string} exists on the list")
        public void the_employee_is_on_the_list_of_employees(String string) {
            projectPlanner.cucumberAddEmployee(string);
            assertTrue(ProjectPlanner.getUsers().stream().anyMatch(user -> user.initials.equalsIgnoreCase(string)));
        }

        @Then("the employee {string} only appears once")
        public void the_employee_is_does_not_appear_twice(String string) {
            ArrayList<User> list = new ArrayList<>();
            for (User user : ProjectPlanner.getUsers()) {
                if (user.getInitials().equalsIgnoreCase(string)) {
                    list.add(user);
                }
            }
            assertEquals(1, list.size());
        }

    }
