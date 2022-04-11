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



    @Given("a project with {string} with id {int} already exists on the list")
    public void a_project_with_with_id_already_exists_on_the_list(String string, Integer id) {
        assertTrue(projectPlanner.getProjects().stream().anyMatch(project -> project.getTitle().equalsIgnoreCase(string) && project.getId() == id));
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
        assertFalse(projectPlanner.getProjects().stream().anyMatch(project -> project.getId() == id));
    }
    
    @Then("the project {int} is on the list of projects")
    public void the_project_is_on_the_list_of_projects(Integer id) {
        projectPlanner.logOut();
        assertFalse(projectPlanner.administratorLoggedIn());
        assertTrue(projectPlanner.getProjects().stream().anyMatch(project -> project.getId() == id));

    }


}
