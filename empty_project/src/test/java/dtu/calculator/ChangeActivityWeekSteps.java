package dtu.calculator;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ChangeActivityWeekSteps {

    ProjectPlanner projectPlanner;


    @When("the project {string} activity's with title {string} start date is set to week {int} and year {int}")
    public void the_project_activity_s_with_title_start_date_is_set_to_week_and_year(String Project_id,
            String Activity_id,
            Integer year, Integer week) throws Exception {

        projectPlanner.getProject(Project_id).getActivity(Activity_id).setStartDate(year,
                week);
    }

    @Then("the project {string} activity's with title {string} date is set to week {int} and year {int}")
    public void the_project_activity_s_with_title_date_is_set_to_week_and_year(String Project_id,
            String Activity_id,
            Integer year, Integer week) throws Exception {
        assertTrue(projectPlanner.getProject(Project_id).getActivity(Activity_id).getStartDate()
                .equalsIgnoreCase("" + year + "-" + week));

    }
}

// public ChangeActivityWeekSteps(projectPlanner projectplanner) {
// this.projectPlanner = projectplanner;
// }

// @Given("that the project leader is logged in")
// public void that_the_project_ddleader_is_logged_in() throws Exception {
// {

// projectPlanner.logIn("HUBE", "PW1234");

// projectPlanner.addEmployee("fred");
// User employee = null;
// for (User employee_ : projectPlanner.getUsers()) {
// if (employee_.getInitials().equalsIgnoreCase("fred")) {
// employee = employee_;
// }
// }
// projectPlanner.getProject(Project_id).projectLeader = employee;
// projectPlanner.logOut();
// projectPlanner.logIn("fred", "01234");
// boolean found = false;
// if (projectPlanner.getProject(Project_id).getProjectleader() == employee) {
// found = true;
// }
// assertTrue(found);

// }
// }

// @Given("there is a project titled {string} with id {string}")
// public void there_is_a_project_titled_with_id(String title, String string2) {
// projectPlanner.cucumberCreateProject(title);
// assertTrue(projectPlanner.getProjects().stream().anyMatch(project ->
// project.title.equalsIgnoreCase(
// title)));
// }

// @Given("the project with id {string} contains an activity titled {string}")
// public void the_project_with_id_contains_an_activity_titled(String
// project_id, String activity_id)
// throws Exception {
// this.Activity_id = activity_id;
// this.Project_id = project_id;
// projectPlanner.getProject(project_id).createActivity(activity_id);
// assertTrue(projectPlanner.getProject(project_id).getActivities().stream()
// .anyMatch(activity -> activity.title.equalsIgnoreCase(
// activity_id)));
// }
