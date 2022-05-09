package dtu.calculator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class checkUserActivity {
    final public CommonSteps commonSteps = new CommonSteps();
    final public ProjectPlanner projectPlanner;

    public checkUserActivity(CommonSteps commonSteps) {
        projectPlanner = commonSteps.projectPlanner;
    }


    @Given("add user {string} to the project {string}")
    public void add_user_to_the_project(String employeeID, String projectID) {
        try {
            projectPlanner.getProject(projectID).addUserToProject(projectPlanner.getUser(employeeID));
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
        
    }

    
    @Given("add user {string} to activity {string} for project {string}")
    public void add_user_to_activity_for_project(String employeeID, String activityTitle, String projectID) {
        
                try {
                    projectPlanner.getProject(projectID).getActivity(activityTitle)
                        .addUserToActivity(projectPlanner.getUser(employeeID));
                } catch (Exception e) {
                    ErrorMessageHolder.setErrorMessage(e.getMessage());
                }

    }

    @Given("user {string} is assigned to activity {string} for project {string}")
    public void user_is_assigned_to_activity_for_project(String employeeID, String activityTitle, String projectID){
        try {
            assertTrue(projectPlanner.getProject(projectID).getActivity(activityTitle).getEmployees().stream()
                .anyMatch(user -> user.getInitials().equals(employeeID)));
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    

    @Given("user {string} is not assigned any activity")
    public void user_is_not_assigned_any_activity(String employeeID) {
        try {
            assertTrue(projectPlanner.getUser(employeeID).getEmployeeActivities().isEmpty());
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }

    }

    @Then("the system displays the list of activities of the employee with id {string}")
    public void the_system_displays_the_list_of_activities_of_the_employee_with_id(String string) {
        try {
            projectPlanner.getActivitiesFromOtherEmployee(projectPlanner.getUser(string));
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }

    }
    
    @Given("user {string} does not exist")
    public void user_does_not_exist(String string) {
        try {
            assertFalse(projectPlanner.getUser(string).getInitials().equals(string));
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
    }


    @Then("the number of activities for user {string} is {int}")
    public void the_number_of_activities_for_user_is(String userID, Integer numberOfActivities) {
        try {
            assertTrue(projectPlanner.getActivitiesFromOtherEmployee(projectPlanner.getUser(userID)).size() == numberOfActivities);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
    }



}
