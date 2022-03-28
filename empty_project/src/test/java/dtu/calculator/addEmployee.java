package dtu.calculator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class addEmployee {

    ProjectPlanner projectPlanner = new ProjectPlanner();

    @Given("that the administrator is logged in")
    public void that_the_administrator_is_logged_in() {
        Administrator Hubert = new Administrator("Hubert", "lolcat");
        projectPlanner.administrator = Hubert;
        assertTrue(projectPlanner.administrator.Login("Hubert", "lolcat"));
    }

    @Given("the employee {string} is not on the list of employees")
    public void the_employee_is_not_on_the_list_of_employees(String string) {
        assertTrue(projectPlanner.uniqueInitials(string));
    }

    @When("the employee {string} is added to the list of employees")
    public void the_employee_is_added_to_the_list_of_employees(String string) {
        projectPlanner.employees.add(new Employee(string));
    }

    @Then("the employee with with id {string} is added to the list of employees")
    public void the_employee_with_with_id_is_added_to_the_list_of_employees(String string) {
        assertTrue(projectPlanner.employees.stream().anyMatch(employee -> employee.initials.equals(string)));
    }

    // @Given("that the administrator is not logged in")
    // public void that_the_administrator_is_not_logged_in() {

    // }

    // @Then("the error message {string} is given")
    // public void the_error_message_is_given(String string) {
    // // Write code here that turns the phrase above into concrete actions
    // throw new io.cucumber.java.PendingException();
    // }

}
