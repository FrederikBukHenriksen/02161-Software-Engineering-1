package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class addEmployeeToProjectSteps {

    final public CommonSteps commonSteps = new CommonSteps();
    final public ProjectPlanner projectPlanner;

    public addEmployeeToProjectSteps(CommonSteps commonSteps) {
        projectPlanner = commonSteps.projectPlanner;
    }

    // FREDERIK START
    @Given("add user {string} to project {string}")
    public void add_user_to_project(String userId, String projectId) throws Exception {
        Project project = projectPlanner.getProject(projectId);
        try {
            User user = projectPlanner.getUser(userId);
            project.addUserToProject(user);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }

    }

    // FREDERIK END

    @Then("the user {string} is in the activity {string} in the project {string}")
    public void the_user_is_in_the_activity_in_the_project(String userId, String ActivityTitle, String ProjectId)
            throws Exception {
        Project project = projectPlanner.getProject(ProjectId);
        User user = projectPlanner.getUser(userId);
        Activity activity = project.getActivity(ActivityTitle);
        assertTrue(activity.getEmployees().contains(user));
    }

    @Given("the user {string} is not in the activity {string} in the project {string}")
    public void the_user_is_not_in_the_activity_in_the_project(String userId, String ActivityTitle, String ProjectId)
            throws Exception {
        Project project = projectPlanner.getProject(ProjectId);
        User user = projectPlanner.getUser(userId);
        Activity activity = project.getActivity(ActivityTitle);
        assertFalse(activity.getEmployees().contains(user));
    }

    @When("the user {string} is added to the activity {string} in the project {string}")
    public void the_user_is_added_to_the_activity_in_the_project(String userId, String ActivityTitle,
            String ProjectId) throws Exception {
        Project project = projectPlanner.getProject(ProjectId);
        User user = projectPlanner.getUser(userId);
        Activity activity = project.getActivity(ActivityTitle);
        activity.addUserToActivity(user);
        assertTrue(activity.getEmployees().contains(user));
    }

    @Given("project leader for the project {string} has chosen an employee with id {string}")
public void project_leader_for_the_project_has_chosen_an_employee_with_id(String projectID, String employeeID) {
        projectPlanner.cucumberAddEmployee(employeeID);
        
    }

    @Given("the employee {string} isn't in the project {string}")
public void the_employee_with_id_isn_t_in_the_project_with_id(String employeeID, String projectID) throws Exception {
    Project project = projectPlanner.getProject(projectID);
        User employee = null;
        for (User employee_ : projectPlanner.getUsers()) {
            if (employee_.getInitials().equalsIgnoreCase(employeeID)) {
                employee = employee_;
            }
        }
        boolean isInProject;
        if (!project.getProjectEmployees().contains(employee)) {
            isInProject = true;
        } else {
            throw new Exception("Employee is already in project");
        }
        assertTrue(isInProject);
    }

    @When("the user {string} is added to the project {string}")
    public void the_user_is_added_to_the_project(String userID, String projectID) throws Exception {
        Project project = projectPlanner.getProject(projectID);
        try {
            User user = projectPlanner.getUser(userID);
            project.addUserToProject(user);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the user {string} is only once in the project {string}")
    public void the_user_is_only_once_in_the_project(String userID, String projectID) throws Exception {
        Project project = projectPlanner.getProject(projectID);
        long count = project.getProjectEmployees().stream().filter(user -> user.getInitials().equalsIgnoreCase(userID))
                .count();
        assertEquals(count, 1);

    }

    @Then("the user {string} is in the project {string}")
    public void the_user_is_assigned_to_the_project(String userInitials, String projectID) throws Exception {
        Project project = projectPlanner.getProject(projectID);
        User user = projectPlanner.getUser(userInitials);
        assertTrue(project.getProjectEmployees().contains(user));
    }

    @Then("the employee with id {string} is not added to the project with id {string}")
    public void the_employee_with_id_is_not_added_to_the_project_with_id(String employeeID, String projectId)
            throws Exception {
        Project project = projectPlanner.getProject(projectId);
        assertFalse(project.getProjectEmployees().stream()
                .anyMatch(employee -> employee.getInitials().equalsIgnoreCase(employeeID)));
    }

    @Given("the project leader for project {string} is {string}")
    public void the_project_leader_for_project_is(String projectID, String userInitials) {
        try {
            Project project = projectPlanner.getProject(projectID);
            User user = projectPlanner.getUser(userInitials);
            project.cucumberSetProjectLeader(user);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Given("the user {string} is not in the project {string}")
    public void the_user_is_not_in_the_project(String userInitials, String projectID) throws Exception {
        Project project = projectPlanner.getProject(projectID);
        User user = projectPlanner.getUser(userInitials);
        assertFalse(project.getProjectEmployees().contains(user));
    }

}
