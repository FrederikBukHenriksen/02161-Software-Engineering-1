package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class removeEmployeeFromProjectSteps {
        ProjectPlanner projectPlanner;


    
     public removeEmployeeFromProjectSteps() {
         projectPlanner = new ProjectPlanner();
     }

     @When("the user {string} is removed from the project {string}")
     public void the_user_is_removed_from_the_project(String userId, String projectId) throws Exception {
         Project project = projectPlanner.getProject(projectId);
         try {
             User user = projectPlanner.getUser(userId);
             project.removeUserFromProject(user);
         } catch (Exception e) {
             ErrorMessageHolder.setErrorMessage(e.getMessage());
         }

     }

     // @Given("the employee with id {string} is in the project with id {string}")
     // public void the_employee_with_id_is_in_the_project_with_id(String employeeID,
     // String projectID) throws Exception {
     // Project project = ProjectPlanner.getProject(projectID);
     // projectPlanner.add
     // projectPlanner.cucumberAddEmployee(employeeID);
     // project.cucumberAddUserToProject(employeeID);
     // assertTrue(project.getProjectEmployees().contains(ProjectPlanner.getUser(employeeID)));
     // }
    
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
