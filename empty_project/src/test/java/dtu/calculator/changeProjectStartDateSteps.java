package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class changeProjectStartDateSteps {
    final public CommonSteps commonSteps = new CommonSteps();
    final public ProjectPlanner projectPlanner;

    public changeProjectStartDateSteps(CommonSteps commonSteps) {
        projectPlanner = commonSteps.projectPlanner;
    }

    @When("the projectleader sets project with id {string} start date to day {int}, month {int}, and year {int}")
    public void the_projectleader_sets_project_with_id_start_date_to_day_month_and_year(String projectID, Integer day,
            Integer month, Integer year) {
                try {
                    projectPlanner.getProject(projectID).setStartDate(year, month, day);
                } catch (Exception e) {
                    ErrorMessageHolder.setErrorMessage(e.getMessage());
                }
        

    }



    @Then("the project with id {string} start date is set to day {int}, month {int}, and year {int}")
    public void the_project_with_id_start_date_is_set_to_day_month_and_year(String projectID, Integer day, Integer month,
            Integer year) throws Exception {
        GregorianCalendar startDate = projectPlanner.dateServer.createDate(year, month, day);
        assertTrue(projectPlanner.getProject(projectID).getStartDate().equals(startDate));
    }

}
