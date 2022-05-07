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
        assertTrue(leave.getLeaveTitle().equals(leaveTitle) && leave.getEndTime().equals(end) && leave.getStartTime().equals(start));
    }

    @When("the employee with id {string} add the leave titled {string} with start date set to day {int}, month {int}, year {int}, and end date set to day {int}, month {int}, year {int} to their list of activities")
    public void the_employee_with_id_add_the_leave_titled_with_start_date_set_to_day_month_year_and_end_date_set_to_day_month_year_to_their_list_of_activities(
            String employeeID, String leaveTitle, Integer startDay, Integer startMonth, Integer startYear,
            Integer endDay,
            Integer endMonth, Integer endYear) throws Exception {
        GregorianCalendar start = new GregorianCalendar(startYear, startMonth, startDay);
        GregorianCalendar end = new GregorianCalendar(endYear, endMonth, endDay);
        ProjectPlanner.getUser(employeeID).createLeave(start, end, leaveTitle);
    }

    @Then("the leave titled {string} is added to the employee with id {string} list of activities")
    public void the_leave_titled_is_added_to_the_employee_with_id_list_of_activities(String leaveTitle,
            String employeeID) throws Exception {
                try {
                    assertTrue(ProjectPlanner.getUser(employeeID).getLeaves().stream().anyMatch(leave -> leave.getLeaveTitle().equals(leaveTitle)));
                } catch (Exception e) {
                    ErrorMessageHolder.setErrorMessage(e.getMessage());
                }
    }

    @Given("the employee with id {string} has a leave titled {string} with start date set to day {int}, month {int}, year {int}, and end date set to day {int}, month {int}, year {int} in their list of activities")
    public void the_employee_with_id_has_a_leave_titled_with_start_date_set_to_day_month_year_and_end_date_set_to_day_month_year_in_their_list_of_activities(
            String employeeID, String leaveTitle, Integer startDay, Integer startMonth, Integer startYear,
            Integer endDay, Integer endMonth, Integer endYear) throws Exception {

        GregorianCalendar start = new GregorianCalendar(startYear, startMonth, startDay);
        GregorianCalendar end = new GregorianCalendar(endYear, endMonth, endDay);

        ProjectPlanner.getUser(employeeID).createLeave(start, end, leaveTitle);
        try {
            assertTrue(ProjectPlanner.getUser(employeeID).getLeaves().stream().anyMatch(leave -> leave.getEndTime().equals(end) && leave.getStartTime().equals(start) && leave.getLeaveTitle().equals(leaveTitle)));
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
        
            
    }

    @When("the employee with id {string} changes the leave titled {string} start date to day {int}, month {int}, year {int}, and end date to day {int}, month {int}, year {int}")
    public void the_employee_with_id_changes_the_leave_titled_start_date_to_day_month_year_and_end_date_to_day_month_year(
            String employeeID, String leaveTitle, Integer startDay, Integer startMonth, Integer startYear,
            Integer endDay, Integer endMonth, Integer endYear) throws Exception {

        GregorianCalendar start = new GregorianCalendar(startYear, startMonth, startDay);
        GregorianCalendar end = new GregorianCalendar(endYear, endMonth, endDay);

        ProjectPlanner.getUser(employeeID).getLeave(leaveTitle).changeEndDate(end);
        ProjectPlanner.getUser(employeeID).getLeave(leaveTitle).changeStartDate(start);

    }

    @Then("the leave titled {string} is changed to start date set to day {int}, month {int}, year {int}, and end date set to day {int}, month {int}, year {int} in the employee with id {string} list of activities")
    public void the_leave_titled_is_changed_to_start_date_set_to_day_month_year_and_end_date_set_to_day_month_year_in_the_employee_with_id_list_of_activities(
            String leaveTitle, Integer startDay, Integer startMonth, Integer startYear,
            Integer endDay, Integer endMonth, Integer endYear, String employeeID) throws Exception {

        GregorianCalendar start = new GregorianCalendar(startYear, startMonth, startDay);
        GregorianCalendar end = new GregorianCalendar(endYear, endMonth, endDay);
            
        try {
            
            Leave leave = ProjectPlanner.getUser(employeeID).getLeave(leaveTitle);
            assertTrue(leave.getEndTime().equals(end) && leave.getStartTime().equals(start));
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
}
