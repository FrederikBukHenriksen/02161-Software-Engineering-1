package dtu.calculator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class removeUserFromActivity {
    final public CommonSteps commonSteps = new CommonSteps();
    final public ProjectPlanner projectPlanner;

    public removeUserFromActivity(CommonSteps commonSteps) {
        projectPlanner = commonSteps.projectPlanner;
    }

    // Frederik Start

    @When("remove user {string} from activity {string} project {string}")
    public void remove_user_from_activity_project(String userId, String activityTitle, String projectId) {
        try {
            Project project = projectPlanner.getProject(projectId);
            User user = projectPlanner.getUser(userId);
            Activity activity = project.getActivity(activityTitle);
            activity.removeEmployee(user);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    // Frederik Stop

}