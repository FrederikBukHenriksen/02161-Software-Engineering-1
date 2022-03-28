package dtu.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Administrator Hubert = new Administrator("Hubert", "lolcat");
        projectPlanner.administrator = Hubert;
        projectPlanner.logIn("Hubert", "lolcat");
        assertEquals(Hubert, projectPlanner.loggedIn);
        assertTrue(projectPlanner.administratorLoggedIn());
    }

    @Given("the employee {string} is not on the list of employees")
    public void the_employee_is_not_on_the_list_of_employees(String string) {
        assertTrue(projectPlanner.uniqueInitials(string));
    }

    @When("the employee {string} is added to the list of employees")
    public void the_employee_is_added_to_the_list_of_employees(String string) throws Exception {
        projectPlanner.addEmployee(string);
    }

    @Then("the employee with id {string} is added to the list of employees")
    public void the_employee_with_with_id_is_added_to_the_list_of_employees(String string) {
        assertTrue(
                projectPlanner.employees.stream().anyMatch(employee -> employee.initials.equals(string.toUpperCase())));
    }

    @Given("that the administrator is not logged in")
    public void that_the_administrator_is_not_logged_in() {
        assertFalse(projectPlanner.administratorLoggedIn());
    }

    @When("the employee {string} is not added to the list of employees")
    public void the_employee_is_not_added_to_the_list_of_employees(String string) {
        assertFalse(
                projectPlanner.employees.stream().anyMatch(employee -> employee.initials.equals(string.toUpperCase())));
    }

    @Then("the error message {string} is given")
    public void the_error_message_is_given(String string) {
        assertTrue(true); // TODO: implementering af exception
    }

}
