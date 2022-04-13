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

    @When("delete the project with id {int}")
    public void delete_the_project_with_id(Integer id) {
        try {
            projectPlanner.removeProject(projectPlanner.getProject(id));
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the project {int} is not on the list of projects")
    public void the_project_is_not_on_the_list_of_projects(Integer id) {
        assertFalse(projectPlanner.getProjects().stream().anyMatch(project -> project.getId().equals(id)));
    }

    @Then("the project {int} is on the list of projects")
    public void the_project_is_on_the_list_of_projects(Integer id) {
        assertTrue(projectPlanner.getProjects().stream().anyMatch(project -> project.getId().equals(id)));

    }

}
