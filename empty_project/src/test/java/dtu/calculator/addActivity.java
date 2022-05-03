package dtu.calculator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class addActivity {


    ProjectPlanner projectPlanner;

    @Before
    public void Before() {
        Memory.reset();
    }

    public addActivity() {
        projectPlanner = new ProjectPlanner();
        DateServer.setDate(2022, 4, 16);
    }

    // Er dette ikke altid sandt? Vi burde vel streame activities, og tjekke om activityTitle findes i streamen
    @Given("there is an activity titled {string}")
    public void there_is_an_activity_titled(String activityTitle) {
        Activity activity = new Activity(activityTitle,null);
        assertTrue(activity.getTitle().equalsIgnoreCase(activityTitle));
    }

    @Given("the activity titled {string} is not on the list of activities for the project with id {string}")
    public void the_activity_titled_is_not_on_the_list_of_activities_for_the_project_with_id(String activityTitle,
            String projectID) throws Exception {
        Project project = ProjectPlanner.getProject(projectID);
        Activity activity = new Activity(activityTitle,null);
        assertTrue(project.getActivities().stream().noneMatch(act -> act.getTitle().equals(activity.getTitle())));
    }

    @When("the activity titled {string} is added to the list of activities for the project with id {string}")
    public void the_activity_titled_is_added_to_the_list_of_activities_for_the_project_with_id(String activityTitle,
            String projectId) throws Exception {
        Project project = ProjectPlanner.getProject(projectId);
        try {
        project.createActivity(activityTitle);

    } catch (Exception e) {
        ErrorMessageHolder.setErrorMessage(e.getMessage());
    }
    }
    
    @Then("the activity titled {string} is added to list of activities for the project with id {string}")
    public void the_activity_titled_is_added_to_list_of_activities_for_the_project_with_id(String activityTitle, String projectId)
            throws Exception {
        Project project = ProjectPlanner.getProject(projectId);
        assertTrue(project.getActivities().stream().anyMatch(act -> act.getTitle().equals(activityTitle)));
    }





    @Given("the activity titled {string} is already on the list of activities for the project with id {string}")
public void the_activity_titled_is_already_on_the_list_of_activities_for_the_project_with_id(String activityTitle, String projectID) throws Exception {
    Project project = ProjectPlanner.getProject(projectID);
    project.createActivity(activityTitle);
    assertTrue(project.getActivities().stream().anyMatch(act -> act.getTitle().equals(activityTitle)));
    }




}