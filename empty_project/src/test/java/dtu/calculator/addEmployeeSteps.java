package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import dtu.calculator.CommonSteps;

import java.util.ArrayList;


public class addEmployeeSteps {

    final public CommonSteps commonSteps = new CommonSteps();
    final public ProjectPlanner projectPlanner;

    public addEmployeeSteps(CommonSteps commonSteps) {
        projectPlanner = commonSteps.projectPlanner;
    }

    @Given("create employee {string}")
    public void create_employee(String userInitials) throws Exception {
        try {
            projectPlanner.addEmployee(userInitials);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("user {string} is in the projectplanner")
    public void the_employee_with_with_id_is_added_to_the_list_of_employees(String string) {
        assertTrue(
                projectPlanner.getUsers().stream().anyMatch(user -> user.initials.equalsIgnoreCase(string)));
    }

    @Given("user {string} is not in the projectplanner")
    public void the_employee_is_not_on_the_list_of_employees(String string) {
        assertFalse(projectPlanner.getUsers().stream().anyMatch(user -> user.initials.equalsIgnoreCase(string)));
    }

    @Then("user {string} only appears once in the projectplanner")
    public void the_employee_is_does_not_appear_twice(String userId) {
        assertEquals(1, projectPlanner.getEmployees().stream()
                .filter(user -> user.getInitials().equalsIgnoreCase(userId)).count());
    }

    }
