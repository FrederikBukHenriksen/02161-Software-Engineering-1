package dtu.calculator;

import io.cucumber.java.en.When;

public class deleteActivitySteps {

    final public CommonSteps commonSteps = new CommonSteps();
    final public ProjectPlanner projectPlanner;

    public deleteActivitySteps(CommonSteps commonSteps) {
        projectPlanner = commonSteps.projectPlanner;
    }

    @When("delete activity {string} from project {string}")
    public void the_activity_is_removed_from_the_project(String activityTitle, String projectId) throws Exception {
        Project project = projectPlanner.getProject(projectId);
        try {
            Activity activity = project.getActivity(activityTitle);
            project.deleteActivity(activity);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
    }


}