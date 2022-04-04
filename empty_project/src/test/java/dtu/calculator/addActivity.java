package dtu.calculator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class addActivity {

    Project project;
    Activity activity;
        
    @Given("that the project leader is logged in")
    public void that_the_project_leader_is_logged_in() {
        assertTrue(true); // placeholder until I figure out where we get/create the projectleader (in Employee- or Project-class)
    }

    @Given("there is a project titled {projectTitle} with id {projectID}")
    public void there_is_a_project_titled_with_id(String projectTitle, String projectID) {
        project = new Project(projectTitle);
        project.id = Integer.parseInt(projectID);
    }

    @Given("there is an activity titled {activityTitle}")
    public void there_is_an_activity_titled(String activityTitle) {
        activity = new Activity(activityTitle);
    }

    @Given("the activity is not on the list of activities")
    public void the_activity_is_not_on_the_list_of_activities() {
        assertTrue(project.activities.stream().noneMatch(act -> act.title.equals(activity.title)));
    }

    @When("the activity is added to the list of activities")
    public void the_activity_is_added_to_the_list_of_activities() {
        project.activities.add(activity);
    }

    @Then("the activity exists in the list of activites for the project")
    public void the_activity_exists_in_the_list_of_activities_for_the_project() {
        assertTrue(project.activities.stream().anyMatch(act -> act.title.equals(activity.title)));
    }


    // @Given("that the project leader is not logged in")
    // public void that_the_project_leader_is_not_logged_in() {
    //     assertFalse(false);
    // }

    // @Then("the error message {string} is given")
    // public void the_error_message_is_given(String string) {
        
    // }

    // @Given("the activity is already on the list of activities")
    // public void the_activity_is_already_on_the_list_of_activities() {
        
    // }

}
