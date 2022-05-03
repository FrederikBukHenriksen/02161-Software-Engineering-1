package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class deleteProjectSteps {

    ProjectPlanner projectPlanner;

    public deleteProjectSteps() {
        this.projectPlanner = new ProjectPlanner();
    }

    @When("delete the project {string}")
    public void delete_the_project(String id) {
        try {
            projectPlanner.removeProject(projectPlanner.getProject(id));
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the project {string} is not on the list of projects")
    public void the_project_is_not_on_the_list_of_projects(String id) {
        assertFalse(projectPlanner.getProjects().stream().anyMatch(project -> project.getId().equals(id)));
    }

    @Then("the project {string} is on the list of projects")
    public void the_project_is_on_the_list_of_projects(String id) {
        assertTrue(projectPlanner.getProjects().stream().anyMatch(project -> project.getId().equals(id)));

    }

}
