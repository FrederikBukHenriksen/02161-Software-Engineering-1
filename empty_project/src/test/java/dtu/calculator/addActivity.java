package dtu.calculator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class addActivity {

    Project project;
    Activity activity;
    ProjectPlanner projectPlanner;

    public addActivity() {
        projectPlanner = new ProjectPlanner();
        DateServer.setDate(2022, 4, 16);
    }

        
    

    @Given("there is a project titled {string} with id {string}")
    public void there_is_a_project_titled_with_id(String projectTitle, String projectID) {
        projectPlanner.cucumberCreateProject(projectTitle + "s");

        boolean found = false;
        for (Project project_ : ProjectPlanner.getProjects()) {
            if (project_.title.equalsIgnoreCase(projectTitle + "s") && project_.getId().equals(projectID)) {
                this.project = project_;
                found = true;
            }
        }
        assertTrue(found);

    }
    
    @Given("that the Project leader is logged in")  //findes et andet sted, har bare ændret p til P
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

    

    @Given("there is an activity titled {string}")
    public void there_is_an_activity_titled(String activityTitle) {
        activity = new Activity(activityTitle);
        assertTrue(activity.getTitle().equalsIgnoreCase(activityTitle));
    }

    @Given("the activity is not on the list of activities")
    public void the_activity_is_not_on_the_list_of_activities() {
        assertTrue(project.activities.stream().noneMatch(act -> act.getTitle().equals(activity.getTitle())));
    }

    @When("the activity is added to the list of activities")
    public void the_activity_is_added_to_the_list_of_activities() {
        project.createActivity(activity.getTitle());
    }
    @Then("the activity is added to list of activities for the project")
    public void the_activity_is_added_to_list_of_activities_for_the_project() {
        assertTrue(project.activities.stream().anyMatch(act -> act.getTitle().equals(activity.getTitle())));
    }


    @Given("that the project leader is not logged in")
    public void that_the_project_leader_is_not_logged_in() {
        ProjectPlanner.logOut();
        assertFalse(project.projectLeaderLoggedIn());
    }


    @Given("the activity is already on the list of activities")
    public void the_activity_is_already_on_the_list_of_activities() {
        project.createActivity(activity.getTitle());
        project.createActivity(activity.getTitle());
    }


    // @Then("the activity exists in the list of activites for the project")
    // public void the_activity_exists_in_the_list_of_activities_for_the_project() {
    //     assertTrue(project.activities.stream().anyMatch(act -> act.getTitle().equals(activity.getTitle())));
    // }


    // // @Given("that the project leader is not logged in")
    // // public void that_the_project_leader_is_not_logged_in() {
    // //     assertFalse(false);
    // // }

    // // @Then("the error message {string} is given")
    // // public void the_error_message_is_given(String string) {
        
    // // }

    // // @Given("the activity is already on the list of activities")
    // // public void the_activity_is_already_on_the_list_of_activities() {
        
    // // }

}