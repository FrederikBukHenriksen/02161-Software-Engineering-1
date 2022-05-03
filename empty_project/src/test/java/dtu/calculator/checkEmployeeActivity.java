package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class checkEmployeeActivity {
    ProjectPlanner projectPlanner;

    @io.cucumber.java.Before
    public void Before() {
        Memory.reset();
    }
    
    public checkEmployeeActivity() {
        projectPlanner = new ProjectPlanner();
    }

    @Given("that employee with id {string} is logged in")
    public void that_employee_with_id_is_logged_in(String employeeID) throws Exception {
        ProjectPlanner.cucumberAddEmployee(employeeID);
        ProjectPlanner.logIn(employeeID, "01234");
        assertTrue(ProjectPlanner.getLoggedIn() == ProjectPlanner.getUser(employeeID));
    }

    @Given("the employee selects another employee with id {string}")
    public void the_employee_selects_another_employee_with_id(String employeeID2) {
        ProjectPlanner.cucumberAddEmployee(employeeID2);
        assertTrue(ProjectPlanner.getUsers().stream().anyMatch(predicate -> predicate.getInitials().equals(employeeID2)));
    }

    @Given("the employee with id {string} has an active activity in their list of activities")
    public void the_employee_with_id_has_an_active_activity_in_their_list_of_activities(String employeeID2) throws Exception {
        User employee2 = ProjectPlanner.getUser(employeeID2);
        projectPlanner.cucumberCreateProject("project");
        Project project = ProjectPlanner.getProject("2022-1");
        project.CucumberCreateActivity("lav en Activity");
        Activity activity = project.getActivity("lav en Activity");
        activity.cucumberAddEmployeeToActivity(employee2);
        assertTrue(employee2.getActivities().stream().anyMatch(act -> act.getTitle().equals("lav en Activity")));
    }

    @Then("the system displays the list of activities of the employee with id {string}")
    public void the_system_displays_the_list_of_activities_of_the_employee_with_id(String employeeID2)
            throws Exception {
        // User employee = new User(null);
        User employee = ProjectPlanner.getUser(employeeID2);
        assertTrue(employee.getActivitiesFromOtherEmployee(employeeID2).stream()
                .anyMatch(predicate -> predicate.equals("lav en Activity")));
    }
    
    @Given("the employee with id {string} has no active activity in their list of activities")
    public void the_employee_with_id_has_no_active_activity_in_their_list_of_activities(String employeeID2)
            throws Exception {
        User employee2 = ProjectPlanner.getUser(employeeID2);
        assertTrue(employee2.getActivities().size() == 0);
    }

    @Then("the system displays that the employee with id {string} has no active activities")
    public void the_system_displays_that_the_employee_with_id_has_no_active_activities(String employeeID2) throws Exception {
        // User employee = new User(null);
        User employee = ProjectPlanner.getUser(employeeID2);
        assertTrue(employee.getActivitiesFromOtherEmployee(employeeID2).size() == 0);
    }    

}
