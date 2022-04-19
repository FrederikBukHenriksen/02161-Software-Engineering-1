package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class addEmployeeToProjectSteps {

    ProjectPlanner projectPlanner;

     @io.cucumber.java.Before
    public void Before() {
        Memory.reset();
    }

    public addEmployeeToProjectSteps() {
        projectPlanner = new ProjectPlanner();
        DateServer.setDate(2022, 4, 16);
    }


    @Given("that there exists a project titled {string} with id {string}")
    public void that_there_exists_a_project_titled_with_id(String string, String id) throws Exception {
        ProjectPlanner.logIn("HUBE", "PW1234");
        projectPlanner.createProject(string);
        boolean found = false;
        for (Project project : ProjectPlanner.getProjects()) {
            if (project.title.equalsIgnoreCase(string) && project.getId().equals(id)) {
                found = true;
            }
        }
        assertTrue(found);
        
    }

    @Given("that the project leader with id {string} for the project {string} is logged in")
public void that_the_project_leader_with_id_for_the_project_is_logged_in(String projectLeaderInitials, String projectID) throws Exception {
        
        projectPlanner.cucumberAddEmployee(projectLeaderInitials);
        User projectLeader = null;
        for (User projectLeader_ : ProjectPlanner.getUsers()) {
            if (projectLeader_.getInitials().equalsIgnoreCase(projectLeaderInitials)) {
                projectLeader = projectLeader_;
            }
        }
        Project project = ProjectPlanner.getProject(projectID);

        project.setProjectLeader(projectLeader);

        ProjectPlanner.logIn(projectLeaderInitials, "01234");
        assertTrue(project.projectLeaderLoggedIn());
        
        
    }

    @Given("project leader for the project {string} has chosen an employee with id {string}")
public void project_leader_for_the_project_has_chosen_an_employee_with_id(String projectID, String employeeID) {
        projectPlanner.cucumberAddEmployee(employeeID);
        
    }

@Given("the employee with id {string} isn't in the project with id {string}")
public void the_employee_with_id_isn_t_in_the_project_with_id(String employeeID, String projectID) throws Exception {
        Project project = ProjectPlanner.getProject(projectID);
        User employee = null;
        for (User employee_ : ProjectPlanner.getUsers()) {
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

    @When("the project leader for the project {string} adds the employee with id {string} to the project")
    public void the_project_leader_for_the_project_adds_the_employee_with_id_to_the_project(String projectId, String employeeID) throws Exception {
        Project project = ProjectPlanner.getProject(projectId);
        projectPlanner.cucumberAddEmployee(employeeID);
        project.addEmployeeToProject(employeeID);
    }
    
    

    @Then("the employee with id {string} is added to the project with id {string}")
    public void the_employee_with_id_is_added_to_the_project_with_id(String employeeID, String projectId)throws Exception {
        Project project = ProjectPlanner.getProject(projectId);
        assertTrue(project.getProjectEmployees().stream().anyMatch(employee -> employee.getInitials().equalsIgnoreCase(employeeID)));
    }



    @Given("that the project leader for the project {string} isn't logged in")
public void that_the_project_leader_for_the_project_isn_t_logged_in(String projectId) throws Exception { 
        ProjectPlanner.logOut();
        Project project = ProjectPlanner.getProject(projectId);
        assertFalse(project.projectLeaderLoggedIn());
    }

    @Then("the employee with id {string} is not added to the project with id {string}")
    public void the_employee_with_id_is_not_added_to_the_project_with_id(String employeeID, String projectId)
            throws Exception {
        Project project = ProjectPlanner.getProject(projectId);
        assertFalse(project.getProjectEmployees().stream().anyMatch(employee -> employee.getInitials().equalsIgnoreCase(employeeID)));
    }
}
