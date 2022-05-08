package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class checkEmployeeActivity {
    final public CommonSteps commonSteps = new CommonSteps();
    final public ProjectPlanner projectPlanner;

    public checkEmployeeActivity(CommonSteps commonSteps) {
        projectPlanner = commonSteps.projectPlanner;
    }

    // @Given("that employee with id {string} is logged in")
    // public void that_employee_with_id_is_logged_in(String employeeID) throws Exception {
    //     projectPlanner.cucumberAddEmployee(employeeID);
    //     projectPlanner.logIn(employeeID, "01234");
    //     assertTrue(projectPlanner.getLoggedIn() == projectPlanner.getUser(employeeID));
    // }

    // @Given("the employee selects another employee with id {string}")
    // public void the_employee_selects_another_employee_with_id(String employeeID2) {
    //     projectPlanner.cucumberAddEmployee(employeeID2);
    //     assertTrue(
    //             projectPlanner.getUsers().stream().anyMatch(predicate -> predicate.getInitials().equals(employeeID2)));
    // }

    // @Given("the employee with id {string} has an active activity in their list of activities")
    // public void the_employee_with_id_has_an_active_activity_in_their_list_of_activities(String employeeID2) throws Exception {
    //     User employee2 = projectPlanner.getUser(employeeID2);
    //     projectPlanner.cucumberCreateProject("project");
    //     Project project = projectPlanner.getProject("2022-1");
    //     project.CucumberCreateActivity("lav en Activity");
    //     Activity activity = project.getActivity("lav en Activity");
    //     activity.cucumberAddEmployeeToActivity(employee2);
    //     assertTrue(employee2.getActivities().stream().anyMatch(act -> act.getTitle().equals("lav en Activity")));
    // }

    // @Then("the system displays the list of activities of the employee with id {string}")
    // public void the_system_displays_the_list_of_activities_of_the_employee_with_id(String employeeID2)
    //         throws Exception {
    //     // User employee = new User(null);
    //     User employee = projectPlanner.getUser(employeeID2);
    //     assertTrue(employee.getActivitiesFromOtherEmployee(employeeID2).stream()
    //             .anyMatch(predicate -> predicate.equals("lav en Activity")));
    // }

    // @Given("the employee with id {string} has no active activity in their list of activities")
    // public void the_employee_with_id_has_no_active_activity_in_their_list_of_activities(String employeeID2)
    //         throws Exception {
    //     User employee2 = projectPlanner.getUser(employeeID2);
    //     assertTrue(employee2.getActivities().size() == 0);
    // }

    // @Then("the system displays that the employee with id {string} has no active activities")
    // public void the_system_displays_that_the_employee_with_id_has_no_active_activities(String employeeID2) throws Exception {
    //     // User employee = new User(null);
    //     User employee = projectPlanner.getUser(employeeID2);
    //     assertTrue(employee.getActivitiesFromOtherEmployee(employeeID2).size() == 0);
    // }  

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
    public void user_is_not_assigned_any_activity(String employeeID){
        try {
            projectPlanner.getUser(employeeID).getActivities().size();
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }

<<<<<<< Updated upstream
    @Given("the employee with id {string} has an active activity in their list of activities")
    public void the_employee_with_id_has_an_active_activity_in_their_list_of_activities(String employeeID2) throws Exception {
        User employee2 = projectPlanner.getUser(employeeID2);
        projectPlanner.cucumberCreateProject("project");
        Project project = projectPlanner.getProject("2022-1");
        project.createActivity("lav en Activity");
        Activity activity = project.getActivity("lav en Activity");
        activity.cucumberAddEmployeeToActivity(employee2);
        assertTrue(employee2.getActivities().stream().anyMatch(act -> act.getTitle().equals("lav en Activity")));
=======
>>>>>>> Stashed changes
    }

    @Then("the system displays the list of activities of the employee with id {string}")
    public void the_system_displays_the_list_of_activities_of_the_employee_with_id(String string) {
        try {
            projectPlanner.getUser("andr").getActivitiesFromOtherEmployee(string);
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


}
