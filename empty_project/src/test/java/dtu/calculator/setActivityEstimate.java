package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class setActivityEstimate {

    final public CommonSteps commonSteps = new CommonSteps();
    final public ProjectPlanner projectPlanner;

    public setActivityEstimate(CommonSteps commonSteps) {
        projectPlanner = commonSteps.projectPlanner;
    }



    @When("the employee {string} changes the estimated time left for the activity titled {string} in the project with id {string} to {double} hours")
    public void the_employee_changes_the_estimated_time_left_for_the_activity_titled_in_the_project_with_id_to_hours(
            String UserID, String activityTitle, String projectID, Double time) throws Exception {
        projectPlanner.logIn(UserID, "01234");
        try {
            projectPlanner.getProject(projectID).getActivity(activityTitle).setActivityEstimate(time);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
        
    }

    @Then("the estimated time left for the activity titled {string} with id {string} is set to {double} hours")
    public void the_estimated_time_left_for_the_activity_titled_with_id_is_set_to_hours(String activityTitle,
            String projectID, Double time) throws Exception {
        assertTrue(projectPlanner.getProject(projectID).getActivity(activityTitle).getActivityEstimate() == time);
    }
}


