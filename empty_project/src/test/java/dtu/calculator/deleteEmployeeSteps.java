package dtu.calculator;

import io.cucumber.java.en.When;

public class deleteEmployeeSteps {

    final public CommonSteps commonSteps = new CommonSteps();
    final public ProjectPlanner projectPlanner;

    public deleteEmployeeSteps(CommonSteps commonSteps) {
        projectPlanner = commonSteps.projectPlanner;
    }

    @When("delete user {string} from the projectplanner")
    public void the_employee_is_removed_from_the_list(String userInitials) {
        try {
            User user = projectPlanner.getUser(userInitials);
            projectPlanner.deleteEmployee(user);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }

    }

}
