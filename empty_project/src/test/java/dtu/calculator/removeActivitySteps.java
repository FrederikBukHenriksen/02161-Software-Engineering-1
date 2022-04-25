package dtu.calculator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class removeActivitySteps {

    Project project;
    Activity activity;
    ProjectPlanner projectPlanner;

    public removeActivitySteps() {
        projectPlanner = new ProjectPlanner();
        DateServer.setDate(2022, 4, 16);
    }

    // findes et andet sted ('addActivity.java' og lidt anderledes i 'addEmployeeToProjectSteps.java'), men med andet navn
    @Given("there is a Project titled {string} with id {string}") // TODO: Fix koden, så vi ikke behøver duplicate steps med små navneændringer
    public void there_is_a_project_titled_with_id(String projectTitle, String projectID) {
        projectPlanner.cucumberCreateProject(projectTitle);

        boolean found = false;
        for (Project project_ : ProjectPlanner.getProjects()) {
            if (project_.title.equalsIgnoreCase(projectTitle) && project_.getId().equals(projectID)) {
                this.project = project_;
                found = true;
            }
        }
        assertTrue(found);
    }

    // findes et andet sted ('addActivity.java' og 'addEmployeeToProjectSteps.java'), men med andet navn
    @Given("that the Project Leader is logged in") // TODO: Fix koden, så vi ikke behøver duplicate steps med små navneændringer
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

    @Given("an activity titled {string} is part of the project")
    public void an_activity_titled_is_part_of_the_project(String activityTitle) {
        removeActivity
        activity = new Activity(activityTitle, project);
        project.createActivity(activityTitle);

        if (project.projectLeaderLoggedIn()) {
            assertTrue(project.getActivities().stream().anyMatch(act -> act.getTitle().equals(activityTitle)));
        }
    }

    @When("the activity is removed from the project")
    public void the_activity_is_removed_from_the_project() {
        if (project.projectLeaderLoggedIn()) { // don't like having to do this, but it's too important not to
            removeActivity
            Activity temp = new Activity("temp", null);
            boolean activityFound = false;
            for (Activity act : project.getActivities()) {
                if (act.getTitle().equals(activity.getTitle())) {
                    temp = act;
                    activityFound = true;
                }
            }
            if (activityFound) {
                project.getActivities().remove(temp);
            } else {
                ErrorMessageHolder.setErrorMessage("The activity doesn't exist in the activity list");
            }
        }
    }

    @Then("the activity is no longer in the list of activities")
    public void the_activity_titled_is_no_longer_in_the_list_of_activities() {
        assertTrue(project.getActivities().stream().noneMatch(act -> act.getTitle().equals(activity.getTitle())));
    }

    @Given("that the Project Leader is not logged in")
    public void that_the_project_leader_is_not_logged_in() {
        ProjectPlanner.logOut();
        assertFalse(project.projectLeaderLoggedIn());
    }

    @Given("an activity titled {string} is not part of the project")
    public void an_activity_titled_is_not_part_of_the_project(String activityTitle) {
        assertTrue(project.getActivities().stream().noneMatch(act -> act.getTitle().equals(activityTitle)));
    }    
}