package dtu.calculator;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class setActivityEstimate {
    ProjectPlanner projectPlanner;



    public setActivityEstimate() {
        projectPlanner = new ProjectPlanner();
    }

    @Given("there is an employee with id {string} who is in the list of employees for the project with id {string}")
    public void there_is_an_employee_with_id_who_is_in_the_list_of_employees_for_the_project_with_id(String employeeID,
            String projectID) throws Exception {
        Project project = projectPlanner.getProject(projectID);
        projectPlanner.cucumberAddEmployee(employeeID);
        User user = projectPlanner.getUser(employeeID);
        project.addUserToProject(user);
        assertTrue(project.getProjectEmployees().contains(projectPlanner.getUser(employeeID)));
    }
    @Given("the employee with id {string} is in the list of employees for the activity titled {string} in the project with id {string}")
    public void the_employee_with_id_is_in_the_list_of_employees_for_the_activity_titled_in_the_project_with_id(
            String employeeID, String activityTitle, String projectID) throws Exception {
        Project project = projectPlanner.getProject(projectID);
        project.createActivity(activityTitle);
        Activity activity = project.getActivity(activityTitle);
        User employee = projectPlanner.getUser(employeeID);
        activity.cucumberAddEmployeeToActivity(employee);
        assertTrue(activity.getEmployees().contains(employee));
    }
    
    @When("the employee changes the estimated time left for the activity titled {string} in the project with id {string} to {double} hours")
    public void the_employee_changes_the_estimated_time_left_for_the_activity_titled_in_the_project_with_id_to_hours(
            String activityTitle, String projectID, Double time) throws Exception {
        Project project = projectPlanner.getProject(projectID);
        Activity activity = project.getActivity(activityTitle);
        activity.setActivityEstimate(time);
    }
    
    @Then("the estimated time left for the activity titled {string} with id {string} is set to {double} hours")
    public void the_estimated_time_left_for_the_activity_titled_with_id_is_set_to_hours(String activityTitle, String projectID, Double time ) throws Exception {
        Project project = projectPlanner.getProject(projectID);
    Activity activity = project.getActivity(activityTitle);
    assertTrue(activity.getActivityEstimate()==time);
}


}
