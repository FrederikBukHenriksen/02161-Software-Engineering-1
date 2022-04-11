package dtu.calculator;
import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.booleanThat;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class addActivitySteps {
    ProjectPlanner projectPlanner;
    Project project;
    User employee;
    Activity activity;
    
    public addActivitySteps() {
        projectPlanner = new ProjectPlanner();
    }

    // @Given("that the project leader is logged in")
    // public void that_the_project_leader_is_logged_in() {
    //    projectPlanner.cucumberAddEmployee("fred");
    //     User employee = null;
    //     for (User employee_ : projectPlanner.getUsers()) {
    //         if (employee_.getInitials().equalsIgnoreCase("fred")){
    //             employee = employee_;
    //         }
    //     }
    //     project.projectLeader = employee;
    //     projectPlanner.logOut();
    //     projectPlanner.logIn("fred", "01234");
    //     boolean found = false;
    //     if (project.getProjectleader() == employee) {
    //         found = true;
    //     }
    //     assertTrue(found);
    // }

    @Given("there is a project titled {string} with id {int}")
    public void there_is_a_project_titled_with_id(String string, Integer int1) {
        projectPlanner.cucumberCreateProject(string);
        boolean found = false;
        for (Project project : projectPlanner.getProjects()) {
            if (project.getTitle().equalsIgnoreCase(string) && project.getId() == int1) {
                this.project = project;
                found = true;
            }
        }
        assertTrue(found);
    }

    @Given("there is an activity titled {string}")
    public void there_is_an_activity_titled(String string) {
        activity = new Activity(string);
    }

        @Given("the activity is not on the list of activities")
    public void the_activity_is_not_on_the_list_of_activities() {
        assertTrue(!project.getActivities().contains(activity));
    }

    @When("the activity is added to the list of activities")
    public void the_activity_is_added_to_the_list_of_activities() {
        project.createActivity(activity.getTitle());
    }

    @Then("the activity is added to list of activities for the project")
    public void the_activity_is_added_to_list_of_activities_for_the_project() {
        assertTrue(project.getActivities().stream().anyMatch(act -> act.getTitle().equals(activity.getTitle())));
    }
}
