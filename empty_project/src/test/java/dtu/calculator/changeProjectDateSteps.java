package dtu.calculator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class changeProjectDateSteps {
    ProjectPlanner projectPlanner;

    public changeProjectDateSteps() {
        projectPlanner = new ProjectPlanner();
    }


  
    // @Given("there is a project titled {string} with id {string}")
    // public void there_is_a_project_titled_with_id(String projectTitle, String
    // projectID) throws Exception {
    // this.projectTitle = projectTitle;
    // this.projectID = projectID;
    // projectPlanner.cucumberCreateProject(projectTitle);
    // this.project = ProjectPlanner.getProject(projectID);

    // boolean found = false;
    // for (Project project_ : ProjectPlanner.getProjects()) {
    // if (project_.title.equalsIgnoreCase(projectTitle) &&
    // project_.getId().equals(projectID)) {
    // this.project = project_;
    // found = true;
    // }
    // }
    // assertTrue(found);
    // }

    // @Given("that the project leader is logged in")
    // public void that_the_project_leader_is_logged_in() throws Exception {
    // ProjectPlanner.cucumberAddEmployee("fred");
    // User projectLeader = null;
    // for (User projectLeader_ : ProjectPlanner.getUsers()) {
    // if (projectLeader_.getInitials().equalsIgnoreCase("fred")) {
    // projectLeader = projectLeader_;
    // }
    // }
    // project.setProjectLeader(projectLeader);

    // ProjectPlanner.logIn("fred", "01234");
    // assertTrue(project.projectLeaderLoggedIn());
    // }

    @When("the projectleader  sets project with id {string} start date to day {int}, month {int}, and year {int}")
    public void the_projectleader_sets_project_with_id_start_date_to_day_month_and_year(String string, Integer int1, Integer int2, Integer int3) throws Exception {
        Project project = projectPlanner.getProject(string);
        System.out.println(project.getTitle());
        project.setStartDate(int1, int2, int3);
    }

    @Then("the project's with id {string} start date is set to day {int}, month {int}, and year {int}")
public void the_project_s_with_id_start_date_is_set_to_day_month_and_year(String string, Integer int1, Integer int2, Integer int3) throws Exception {
    Project project = projectPlanner.getProject(string);

        String date = ("" + int1 + "/" + int2 + "/" + int3);

        Boolean found = false;
        if (project.getStartDate().equals(date)) {
            found = true;
        }

        assertTrue("Found start date of project", found);

    }
}
