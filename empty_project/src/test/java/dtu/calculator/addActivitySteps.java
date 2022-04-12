package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class addActivitySteps {

    ProjectPlanner pp = new ProjectPlanner();
    Project project;
    Activity activity;
    boolean projectLeaderLoggedIn;
    
    @Given("that the project leader is logged in")
    public void that_the_project_leader_is_logged_in() throws Exception{
        pp.logIn("HUBE", "PW1234");
        pp.addEmployee("EMPL");

        project.projectLeader = (Employee) ProjectPlanner.getUsers().get(ProjectPlanner.getUsers().size()-1); // ugly way to get latest added employee
        pp.logIn("EMPL", "01234");
        // TODO: Check if logIn() method works after login has been fixed

        assertEquals("EMPL", ProjectPlanner.getLoggedIn().getInitials());
        projectLeaderLoggedIn = ProjectPlanner.employeeLoggedIn() && project.projectLeader == ProjectPlanner.getLoggedIn();
        assertTrue(projectLeaderLoggedIn);
    }

    @Given("there is a project titled {string} with id {string}")
    public void there_is_a_project_titled_with_id(String projectTitle, String projectID) {
        project = new Project(projectTitle);
        project.id = Integer.parseInt(projectID);
    }

    @Given("there is an activity titled {string}")
    public void there_is_an_activity_titled(String activityTitle) {
        activity = new Activity(activityTitle);
    }

    @Given("the activity is not on the list of activities")
    public void the_activity_is_not_on_the_list_of_activities() {
        assertTrue(project.getActivities().stream().noneMatch(act -> act.title.equalsIgnoreCase(activity.title))); // using 'noneMatch()' here
    }

    @When("the activity is added to the list of activities")
    public void the_activity_is_added_to_the_list_of_activities() {
        try {
            project.addActivity(activity.title);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the activity exists in the list of activities for the project")
    public void the_activity_exists_in_the_list_of_activities_for_the_project() {
        assertTrue(project.getActivities().stream().anyMatch(act -> act.title.equalsIgnoreCase(activity.title))); // using 'anyMatch()' here
    }

    @Given("that the project leader is not logged in")
    public void that_the_project_leader_is_not_logged_in() throws Exception {
        pp.logIn("HUBE", "PW1234");
        pp.addEmployee("EMPL");

        project.projectLeader = null;
        pp.logIn("EMPL", "01234");
        // TODO: Check if logIn() method works after login has been fixed

        assertEquals("EMPL", ProjectPlanner.getLoggedIn().getInitials());
        assertFalse(ProjectPlanner.employeeLoggedIn() && project.projectLeader == ProjectPlanner.getLoggedIn());
    }

    @Given("the activity is already on the list of activities")
    public void the_activity_is_already_on_the_list_of_activities() throws Exception {
        project.addActivity(activity.title);
    }

    // // this exists in addEmployeeSteps already, so it's not included here -- Nevermind, using the pre-existing one causes issues
    // @Then("the error message {string} is returned")
    // public void the_error_message_is_returned(String errorMsg) {
    //     assertEquals(errorMsg, errorMessageHolder.getErrorMessage());
    // }
}
