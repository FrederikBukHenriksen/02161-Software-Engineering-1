package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

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


    @Given("the project {string} with id {int} does not already exists on the list")
    public void the_project_does_not_already_exist(String string, int id) {
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

    @Then("the project titled {string} with id {int} is be added to the list of projects")
    public void the_project_titled_with_id_is_be_added_to_the_list_of_projects(String title, Integer id) {
        boolean found = false;
        for (Project project : projectPlanner.getProjects()) {
            if (project.title.equalsIgnoreCase(title) && project.id == id) {
                found = true;
            }
        }
        assertTrue(found);
    }

    // The project is already created with a new ID

    @Given("a project {string} already exists on the list")
    public void the_project_already_exists_on_the_list(String string) throws Exception {
        projectPlanner.createProject(string);
        assertTrue(projectPlanner.getProjects().stream().anyMatch(project -> project.title.equalsIgnoreCase(string)));
    }

    // Administrator not logged in



    @Then("the project titled {string} with id {int} is not added to the list of projects")
    public void the_project_titled_with_id_is_not_added_to_the_list_of_projects(String title, Integer id) {
        boolean found = false;
        for (Project project : projectPlanner.getProjects()) {
            if (project.title.equalsIgnoreCase(title) || project.id == id) {
                found = true;
            }
        }
        assertFalse(found);
    }

}
