package dtu.calculator;

import io.cucumber.java.en.When;

public class deleteProjectSteps {

    final public CommonSteps commonSteps = new CommonSteps();
    final public ProjectPlanner projectPlanner;

    public deleteProjectSteps(CommonSteps commonSteps) {
        projectPlanner = commonSteps.projectPlanner;
    }

    @When("delete project {string} from projectplanner")
    public void delete_the_project(String id) {
        try {
            projectPlanner.deleteProject(projectPlanner.getProject(id));
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
    }


}
