package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.booleanThat;

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
    }

    @Given("that there exists a project titled {string} with id {int}")
    public void that_there_exists_a_project_titled_with_id(String string, Integer int1) throws Exception {
        projectPlanner.logIn("HUBE", "PW1234");
        projectPlanner.createProject(string);
        boolean found = false;
        for (Project project : projectPlanner.getProjects()) {
            if (project.getTitle().equalsIgnoreCase(string) && project.getId() == int1) {
                this.project = project;
                found = true;
            }
        }
        assertTrue(found);

        
    }

    @Given("that the project leader is logged in")
    public void that_the_project_leader_is_logged_in() throws Exception {
        
        projectPlanner.addEmployee("fred");
        User employee = null;
        for (User employee_ : projectPlanner.getUsers()) {
            if (employee_.getInitials().equalsIgnoreCase("fred")){
                employee = employee_;
            }
        }
        project.projectLeader = employee;
        projectPlanner.logOut();
        projectPlanner.logIn("fred", "01234");
        boolean found = false;
        if (project.getProjectleader() == employee) {
            found = true;
        }
        assertTrue(found);
        
    }

    @Given("project leader has chosen an employee with id {string}")
    public void project_leader_has_chosen_an_employee_with_id(String string) throws Exception {
        for (User employee : projectPlanner.getUsers()) {
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
    public void the_project_leader_adds_the_employee_to_the_project() {
       project.projectEmployees.add(employee);
    }

    @Then("the employee is added to the project")
    public void the_employee_is_added_to_the_project() {
        assertTrue(project.projectEmployees.contains(employee));
    }
}
