package dtu.calculator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

    

public class addEmployeeToActivity {

    Project project;
    Activity activity;
    ProjectPlanner projectPlanner;
    User employee;

    @Before
    public void Before() {
        Memory.reset();
    }

    public addEmployeeToActivity() {
        projectPlanner = new ProjectPlanner();
        DateServer.setDate(2022, 4, 16);
        projectPlanner.cucumberCreateProject("Project1");

    }

    // @Given("that there exists a Project titled {string} with id {string}") //findes et andet sted, har bare ændret p til P //TODO løs den her
    // public void that_there_exists_a_project_titled_with_id(String string, String string2) throws Exception {
    //     projectPlanner.cucumberCreateProject(string);
    //     boolean found = false;
    //     for (Project project : ProjectPlanner.getProjects()) {
    //         if (project.title.equalsIgnoreCase(string) && project.getId().equals(string2)) {
    //             this.project = project;
    //             found = true;
    //         }
    //     }
    //     assertTrue(found);
    // }
    


    @Given("the project contains an activity titled {string}")
    public void the_project_contains_an_activity_titled(String string) {
        project.createActivity(string);
        assertTrue(project.getActivities().stream().anyMatch(act -> act.getTitle().equals(string)));
    }

    @Given("there is an employee with id {string}")
    public void there_is_an_employee_with_id(String string) {
        projectPlanner.cucumberAddEmployee(string);
        for(User user : ProjectPlanner.getUsers()) {
            if(user.getInitials().equals(string)) {
                employee = user;
            }
        }
        assertTrue(ProjectPlanner.getUsers().stream().anyMatch(user -> user.getInitials().equals(string)));
    }
    @Given("the employee is not already on the activity's list of employees")
    public void the_employee_is_not_already_on_the_activity_s_list_of_employees() {
        activity.getEmployees().contains(employee);
    }

    @When("the employee is added to the list of employees for the activity")
    public void the_employee_is_added_to_the_list_of_employees_for_the_activity() {
        activity.addEmployee(employee);
    }

    @Then("the employee with id {string} is added to list of employees for the activity")
    public void the_employee_with_id_is_added_to_list_of_employees_for_the_activity(String string) {
        assertTrue(activity.getEmployees().stream().anyMatch(user -> user.getInitials().equalsIgnoreCase(string)));
    }
}