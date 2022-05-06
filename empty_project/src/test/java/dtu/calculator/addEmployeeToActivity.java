package dtu.calculator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

    

public class addEmployeeToActivity {
    final public CommonSteps commonSteps = new CommonSteps();
    final public ProjectPlanner projectPlanner;

    public addEmployeeToActivity(CommonSteps commonSteps) {
        projectPlanner = commonSteps.projectPlanner;
    }

    // Frederik Start

    @Given("add user {string} to activity {string} in project {string}")
    public void add_user_to_activity_in_project(
            String employeeID, String activityTitle, String projectId) {
        try {
            Project project = projectPlanner.getProject(projectId);
            User user = projectPlanner.getUser(employeeID);
            Activity activity = project.getActivity(activityTitle);
            activity.addUserToActivity(user);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("user {string} is in activity {string} in project {string}")
    public void user_is_in_activity_in_project(String employeeID, String activityTitle, String projectId) {
        Activity activity = null;
        User user = null;
        try {
            Project project = projectPlanner.getProject(projectId);
            user = projectPlanner.getUser(employeeID);
            activity = project.getActivity(activityTitle);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertTrue(activity.getEmployees().contains(user));

    }

    @Then("user {string} is not in activity {string} in project {string}")
    public void user_is_not_in_activity_in_project(String employeeID, String activityTitle, String projectId) {
        Activity activity = null;
        User user = null;
        try {
            Project project = projectPlanner.getProject(projectId);
            user = projectPlanner.getUser(employeeID);
            activity = project.getActivity(activityTitle);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertFalse(activity.getEmployees().contains(user));

    }

    @Then("user {string} only appears once in activity {string} in project {string}")
    public void user_only_appears_once_in_activity_in_project(String employeeID, String activityTitle,
            String projectId) {
        Activity activity = null;
        User user = null;
        try {
            Project project = projectPlanner.getProject(projectId);
            user = projectPlanner.getUser(employeeID);
            activity = project.getActivity(activityTitle);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
        assertEquals(activity.getEmployees().stream().filter(emp -> emp.getInitials().equalsIgnoreCase(employeeID))
                .count(), 1);
    }

    // Frederik End

}