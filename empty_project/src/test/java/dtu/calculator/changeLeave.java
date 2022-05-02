package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class changeLeave {

        ProjectPlanner projectPlanner;

    @io.cucumber.java.Before
    public void Before() {
        Memory.reset();
    }
    
    public changeLeave() {
        projectPlanner = new ProjectPlanner();
        DateServer.setDate(2022, 4, 16);
       
    }

    @Given("there is leave titled {string} with start date set to day {int}, month {int}, year {int}, and end date set to day {int}, month {int}, year {int}")
    public void there_is_leave_titled_with_start_date_set_to_day_month_year_and_end_date_set_to_day_month_year(
            String leaveTitle, Integer startDay, Integer startMonth, Integer startYear, Integer endDay,
            Integer endMonth, Integer endYear) {
        GregorianCalendar start = new GregorianCalendar(startYear, startMonth, startDay);
        GregorianCalendar end = new GregorianCalendar(endYear, endMonth, endDay);
        Leave leave = new Leave(start, end, leaveTitle);
        assertTrue(leave.title.equals(leaveTitle));
        assertTrue(leave.title.equals(leaveTitle) && leave.startTime.equals(start) && leave.endTime.equals(end));
    }

    @When("the employee with id {string} add the leave titled {string} with start date set to day {int}, month {int}, year {int}, and end date set to day {int}, month {int}, year {int} to their list of activities")
    public void the_employee_with_id_add_the_leave_titled_with_start_date_set_to_day_month_year_and_end_date_set_to_day_month_year_to_their_list_of_activities(
            String employeeID, String leaveTitle, Integer startDay, Integer startMonth, Integer startYear, Integer endDay,
            Integer endMonth, Integer endYear) throws Exception {
        GregorianCalendar start = new GregorianCalendar(startYear, startMonth, startDay);
        GregorianCalendar end = new GregorianCalendar(endYear, endMonth, endDay);
        ProjectPlanner.getUser(employeeID).createLeave(start, end, leaveTitle);
    }
    @Then("the leave titled {string} is added to the employee with id {string} list of activities")
    public void the_leave_titled_is_added_to_the_employee_with_id_list_of_activities(String leaveTitle,
            String employeeID) throws Exception {
        assertTrue(ProjectPlanner.getUser(employeeID).getLeave().stream().anyMatch(act -> act.equals(leaveTitle)));
    }



    

    
}
