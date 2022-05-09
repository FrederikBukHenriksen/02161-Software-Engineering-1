package dtu.calculator;

import io.cucumber.java.en.When;

public class removeEmployeeFromProjectSteps {
    final public CommonSteps commonSteps = new CommonSteps();
    final public ProjectPlanner projectPlanner;

    public removeEmployeeFromProjectSteps(CommonSteps commonSteps) {
        projectPlanner = commonSteps.projectPlanner;
    }

    @When("remove user {string} from project {string}")
     public void the_user_is_removed_from_the_project(String userId, String projectId) throws Exception {
         try {
             Project project = projectPlanner.getProject(projectId);
             User user = projectPlanner.getUser(userId);
             project.removeUserFromProject(user);
         } catch (Exception e) {
             ErrorMessageHolder.setErrorMessage(e.getMessage());
         }

     }



}
