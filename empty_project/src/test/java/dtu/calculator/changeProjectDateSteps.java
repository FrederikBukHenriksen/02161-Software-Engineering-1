package dtu.calculator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class changeProjectDateSteps {
    Project project;
    Activity activity;
    ProjectPlanner projectPlanner;
    String projectTitle; 
    String projectID;


    public changeProjectDateSteps()  {
        Memory.reset();
        projectPlanner = new ProjectPlanner();
        DateServer.setDate(2022, 4, 16);
    }

    @Given("there is a project titled {string} with id {string}")
    public void there_is_a_project_titled_with_id(String projectTitle, String projectID) throws Exception {
        this.projectTitle = projectTitle;
        this.projectID = projectID;
        projectPlanner.cucumberCreateProject(projectTitle);
        this.project = ProjectPlanner.getProject(projectID);


        boolean found = false;
        for (Project project_ : ProjectPlanner.getProjects()) {
            if (project_.title.equalsIgnoreCase(projectTitle) && project_.getId().equals(projectID)) {
                this.project = project_;
                found = true;
            }
        }
        assertTrue(found);
    }
    
    @Given("that the project leader is logged in")
    public void that_the_project_leader_is_logged_in() throws Exception {
        ProjectPlanner.cucumberAddEmployee("fred");
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

    @When("the projectleader  set start date to day {string}, month {string}, and year {string}")
        public void the_projectleader_set_start_date_to_day_month_and_year(String string, String string2, String string3) {
        project.setStartDate(string, string2, string3);
       

    }

    @Then("the project's start date is set to day {string}, month {string}, and year {string}")
      public void the_project_s_start_date_is_set_to_day_month_and_year(String string, String string2, String string3) throws Exception {
        String date = string + "/" + string2 + "/" + string3;

        Boolean found = false;
        project = ProjectPlanner.getProject(projectID);
            if (project.getStartDate().equals(date)) {
                found = true;
            }
        

        assertTrue("Found start date of project", found);

    
    }

    @Given("there is a project already created titled {string} with id {string}")
    public void there_is_a_project_already_created_titled_with_id(String string, String string2) throws Exception {
        Boolean found = false;
        projectPlanner.cucumberCreateProject(string);
        project = ProjectPlanner.getProject(string2);
        if (project.title.equalsIgnoreCase(string) && project.getId().equals(string2)) {
            found = true;
        }
    

    assertTrue("Found project", found);
    }

    @Given("that the project leader is not logged in")
    public void that_the_project_leader_is_not_logged_in() throws Exception {
        ProjectPlanner.logOut();
        assertFalse(project.projectLeaderLoggedIn());
    }

       @When("the sets start date is set to day {string}, month {string}, and year {string}")
    public void the_sets_start_date_is_set_to_day_month_and_year(String string, String string2, String string3) {

    }

    @Then("the error message {string} is given to user")
    public void the_error_message_is_given_to_user(String string) {
        project.setStartDate("1" , "1" , "1");
        assertTrue(ErrorMessageHolder.getErrorMessage().equals(string)); 
    }
}
