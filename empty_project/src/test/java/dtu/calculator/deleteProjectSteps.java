package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class deleteProjectSteps {

    ProjectPlanner projectPlanner;

    public deleteProjectSteps(ProjectPlanner projectplanner, ErrorMessageHolder errorMessageHolder) {
        this.projectPlanner = projectplanner;
    }

    @When("delete the project with id {string}")
    public void delete_the_project_with_id(String string) {
        try {
            projectPlanner.removeProject(projectPlanner.getProject(string));
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the project {string} is not on the list of projects")
    public void the_project_is_not_on_the_list_of_projects(String string) {
        assertFalse(projectPlanner.getProjects().stream().anyMatch(project -> project.id.equals(string)));
    }

    @Then("the project {string} is on the list of projects")
    public void the_project_is_on_the_list_of_projects(String string) {
        assertTrue(projectPlanner.getProjects().stream().anyMatch(project -> project.id.equals(string)));

    }

}
