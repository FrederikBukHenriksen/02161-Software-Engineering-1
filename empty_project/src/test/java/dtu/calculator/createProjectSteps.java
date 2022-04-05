package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class createProjectSteps {

    ProjectPlanner projectPlanner;
    ErrorMessageHolder errorMessageHolder;

    public createProjectSteps(ProjectPlanner projectplanner, ErrorMessageHolder errorMessageHolder) {
        this.projectPlanner = projectplanner;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("the date is year {int} month {int} day {int}")
    public void the_date_is_year_month_day(Integer year, Integer month, Integer day) {
        DateServer.setDate(2022, 1, 1);
    }

    @Given("the project {string} with id {string} does not already exists on the list")
    public void the_project_does_not_already_exist(String string, String id) {
        assertTrue(projectPlanner.uniqueProject(string, id));
    }

    @When("create a project titled {string}")
    public void the_administrator_creates_a_project_titled_with_id(String title) {
        try {
            projectPlanner.createProject(title);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }

    }

    @Then("the project {string} with id {string} is added to the list of projects")
    public void the_project_titled_with_id_is_be_added_to_the_list_of_projects(String title, String id) {
        assertTrue(projectPlanner.getProjects().stream()
                .anyMatch(project -> project.title.equalsIgnoreCase(title) && project.id
                        .equals(id)));
    }

    // The project is already created with a new ID

    @Given("a project {string} with id {string} already exists on the list")
    public void the_project_already_exists_on_the_list(String title, String id) throws Exception {
        projectPlanner.createProject(title);
        assertTrue(projectPlanner.getProjects().stream().anyMatch(project -> project.title.equalsIgnoreCase(
                title)));
    }

    // Administrator not logged in



    @Then("the project {string} with id {string} is not added to the list of projects")
    public void the_project_titled_with_id_is_not_added_to_the_list_of_projects(String title, String id) {
        assertFalse(projectPlanner.getProjects().stream()
                .anyMatch(project -> project.title.equalsIgnoreCase(title) && project.id
                        .equals(id)));
    }

}
