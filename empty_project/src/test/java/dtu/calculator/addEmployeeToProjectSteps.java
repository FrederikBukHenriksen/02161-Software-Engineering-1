package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class addEmployeeToProjectSteps {

    ProjectPlanner projectPlanner;
    Project project;
    User employee;


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
                this.project = project;
                found = true;
            }
        }
        assertTrue(found);
        
    }

    @Given("that the project leader is logged in")
    public void that_the_project_leader_is_logged_in() throws Exception {
        
        projectPlanner.cucumberAddEmployee("fred");
        User projectLeader = null;
        for (User projectLeader_ : ProjectPlanner.getUsers()) {
            if (projectLeader_.getInitials().equalsIgnoreCase("fred")) {
                projectLeader = projectLeader_;
            }
        }
        project.setProjectLeader(projectLeader);

        ProjectPlanner.logIn("fred", "01234");
        assertTrue(project.projectLeaderLoggedIn());
        
        
    }

    @Given("project leader has chosen an employee with id {string}")
    public void project_leader_has_chosen_an_employee_with_id(String string) throws Exception {
        projectPlanner.cucumberAddEmployee(string);
        for (User employee : ProjectPlanner.getUsers()) {
            if (employee.getInitials().equalsIgnoreCase(string)) {
                this.employee = employee;
            }
        }
    }

    @Given("the employee isn't in the project")
    public void the_employee_isn_t_in_the_project() throws Exception {
        boolean isInProject;
        if (!project.projectEmployees.contains(employee)) {
            isInProject = true;
        } else {
            throw new Exception("Employee is already in project");
        }
        assertTrue(isInProject);
    }

    @When("the project leader adds the employee to the project")
    public void the_project_leader_adds_the_employee_to_the_project() throws Exception {

        project.addEmployeeToProject(employee.getInitials());
    }
    
    

    @Then("the employee is added to the project")
    public void the_employee_is_added_to_the_project() {
        assertTrue(project.projectEmployees.contains(employee));
    }



    @Given("that the project leader isn't logged in")
    public void that_the_project_leader_isn_t_logged_in() {
        ProjectPlanner.logOut();
        assertFalse(project.projectLeaderLoggedIn());
    }

    @Then("the employee is not added to the project")
    public void the_employee_is_not_added_to_the_project() {
        assertFalse(project.projectEmployees.contains(employee));
    }
}
