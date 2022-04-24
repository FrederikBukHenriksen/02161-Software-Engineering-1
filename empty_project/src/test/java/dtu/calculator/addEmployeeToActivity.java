package dtu.calculator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

    

public class addEmployeeToActivity {



    ProjectPlanner projectPlanner;


    @Before
    public void Before() {
        Memory.reset();
    }

    public addEmployeeToActivity() {
        projectPlanner = new ProjectPlanner();
        DateServer.setDate(2022, 4, 16);
        projectPlanner.cucumberCreateProject("Project1");

    }


    


    @Given("the project with id {string} contains an activity titled {string}")
    public void the_project_with_id_contains_an_activity_titled(String projectId, String activityTitle) throws Exception {
        Project project = ProjectPlanner.getProject(projectId);
        project.CucumbercreateActivity(activityTitle);
        assertTrue(project.getActivities().stream().anyMatch(act -> act.getTitle().equals(activityTitle)));
    }

    @Given("there is an employee with id {string}")
    public void there_is_an_employee_with_id(String employeeID) {
        projectPlanner.cucumberAddEmployee(employeeID);
        assertTrue(ProjectPlanner.getUsers().stream().anyMatch(user -> user.getInitials().equals(employeeID)));
    }
    @Given("the employee with id {string} is not already on the activity with the title {string} in the project with id {string} list of employees")
    public void the_employee_with_id_is_not_already_on_the_activity_with_the_title_in_the_project_with_id_list_of_employees(String employeeID, String activityTitle, String projectId) throws Exception {
        Project project = ProjectPlanner.getProject(projectId);
        User employee = ProjectPlanner.getUser(employeeID);
        Activity activity = project.getActivity(activityTitle);
        assertTrue(activity.getEmployees().stream().noneMatch(user -> user.getInitials().equalsIgnoreCase(employee.getInitials())));
    }

    @When("the employee with id {string} is added to the list of employees for the activity with the title {string} in the project with id {string}")
    public void the_employee_with_id_is_added_to_the_list_of_employees_for_the_activity_with_the_title_in_the_project_with_id(
            String employeeID, String activityTitle, String projectId) throws Exception {
        Project project = ProjectPlanner.getProject(projectId);
        User employee = ProjectPlanner.getUser(employeeID);
        Activity activity = project.getActivity(activityTitle);
        activity.addEmployeeToActivity(employee);
    }

    @Then("employee with id {string} is added to the list of employees for the activity with the title {string} in the project with id {string}")
    public void employee_with_id_is_added_to_the_list_of_employees_for_the_activity_with_the_title_in_the_project_with_id(
            String employeeID, String activityTitle, String projectId) throws Exception {
        Project project = ProjectPlanner.getProject(projectId);
        User employee = ProjectPlanner.getUser(employeeID);
        Activity activity = project.getActivity(activityTitle);
        assertTrue(activity.getEmployees().stream()
                .anyMatch(user -> user.getInitials().equalsIgnoreCase(employee.getInitials())));
    }
    

    @Given("the employee with id {string} is already on the activity with the title {string} in the project with id {string} list of employees")
    public void the_employee_with_id_is_already_on_the_activity_with_the_title_in_the_project_with_id_list_of_employees(
            String employeeID, String activityTitle, String projectId) throws Exception {
        Project project = ProjectPlanner.getProject(projectId);
        User employee = ProjectPlanner.getUser(employeeID);
        Activity activity = project.getActivity(activityTitle);
        activity.addEmployeeToActivity(employee);
        assertTrue(activity.getEmployees().stream().anyMatch(user -> user.getInitials().equalsIgnoreCase(employee.getInitials())));
}
}