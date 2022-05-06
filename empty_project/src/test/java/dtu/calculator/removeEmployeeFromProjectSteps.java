package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class removeEmployeeFromProjectSteps {
    final public CommonSteps commonSteps = new CommonSteps();
    final public ProjectPlanner projectPlanner;

    public removeEmployeeFromProjectSteps(CommonSteps commonSteps) {
        projectPlanner = commonSteps.projectPlanner;
    }

    @When("remove user {string} from project {string}")
     public void the_user_is_removed_from_the_project(String userId, String projectId) throws Exception {
         try {
             Project project = projectPlanner.getProject(projectId);
             User user = projectPlanner.getUser(userId);
             project.removeUserFromProject(user);
         } catch (Exception e) {
             ErrorMessageHolder.setErrorMessage(e.getMessage());
         }

     }

     @When("the project leader for the project {string} removes the employee with id {string} to the project")
     public void the_project_leader_for_the_project_removes_the_employee_with_id_to_the_project(String projectID,
             String employeeID) throws Exception {
         Project project = projectPlanner.getProject(projectID);
         project.removeUserFromProject(projectPlanner.getUser(employeeID));
     }
    @Then("the employee with id {string} is removed from the project with id {string}")
    public void the_employee_with_id_is_removed_from_the_project_with_id(String employeeID, String projectID) throws Exception {
        Project project = projectPlanner.getProject(projectID);
        User employee = projectPlanner.getUser(employeeID);
        assertFalse(project.getProjectEmployees().contains(employee));
    }


}
