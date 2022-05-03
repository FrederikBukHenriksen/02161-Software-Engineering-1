package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class removeEmployeeFromProjectSteps {
        ProjectPlanner projectPlanner;

     @io.cucumber.java.Before
     public void Before() {
         Memory.reset();
     }
    
     public removeEmployeeFromProjectSteps() {
         projectPlanner = new ProjectPlanner();
         DateServer.setDate(2022, 4, 16);
     }
     
     @Given("the employee with id {string} is in the project with id {string}")
     public void the_employee_with_id_is_in_the_project_with_id(String employeeID, String projectID) throws Exception {
         Project project = ProjectPlanner.getProject(projectID);
         projectPlanner.cucumberAddEmployee(employeeID);
         project.cucumberAddUserToProject(employeeID);
         assertTrue(project.getProjectEmployees().contains(ProjectPlanner.getUser(employeeID)));
     }
    
     @When("the project leader for the project {string} removes the employee with id {string} to the project")
     public void the_project_leader_for_the_project_removes_the_employee_with_id_to_the_project(String projectID,
             String employeeID) throws Exception {
         Project project = ProjectPlanner.getProject(projectID);
         project.removeUserFromProject(ProjectPlanner.getUser(employeeID));
     }
    @Then("the employee with id {string} is removed from the project with id {string}")
    public void the_employee_with_id_is_removed_from_the_project_with_id(String employeeID, String projectID) throws Exception {
        Project project = ProjectPlanner.getProject(projectID);
        User employee = ProjectPlanner.getUser(employeeID);
        assertFalse(project.getProjectEmployees().contains(employee));
    }


}
