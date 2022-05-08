package dtu.calculator;

import static dtu.calculator.ErrorMessageHolder.setErrorMessage;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class createProjectSteps {

    final public CommonSteps commonSteps = new CommonSteps();
    final public ProjectPlanner projectPlanner;

    public createProjectSteps(CommonSteps commonSteps) {
        projectPlanner = commonSteps.projectPlanner;
    }

    @When("create a project titled {string}")
    public void the_administrator_creates_a_project_titled_with_id(String title) {
        try {
            projectPlanner.createProject(title);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }

    }

    @Then("the project {string} with id {string} is in the projectplanner")
    public void the_project_titled_with_id_is_be_added_to_the_list_of_projects(String projectTitle, String projectId) {
        assertTrue(projectPlanner.getProjects().stream()
                .anyMatch(project -> project.getTitle().equalsIgnoreCase(projectTitle) && project.getId()
                        .equalsIgnoreCase(projectId)));
    }


    @Then("the project {string} with id {string} is not in the projectplanner")
    public void the_project_titled_with_id_is_not_added_to_the_list_of_projects(String title, String id) {
        assertFalse(projectPlanner.getProjects().stream()
                .anyMatch(project -> project.getTitle().equalsIgnoreCase(title) && project.getId() == id));
    }

}
