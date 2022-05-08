package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class changeLeaveSteps {
    

    final public CommonSteps commonSteps = new CommonSteps();
    final public ProjectPlanner projectPlanner;

    public changeLeaveSteps(CommonSteps commonSteps) {
        projectPlanner = commonSteps.projectPlanner;
    }

        @Given("there is leave titled {string} with start date set to day {int}, month {int}, year {int}, and end date set to day {int}, month {int}, year {int}")
    public void there_is_leave_titled_with_start_date_set_to_day_month_year_and_end_date_set_to_day_month_year(
            String leaveTitle, Integer startDay, Integer startMonth, Integer startYear, Integer endDay,
            Integer endMonth, Integer endYear) throws Exception {
        CustomCalendar start = new CustomCalendar(startYear, startMonth, startDay);
        CustomCalendar end = new CustomCalendar(endYear, endMonth, endDay);
        Leave leave = new Leave(start, end, projectPlanner,leaveTitle);
        assertTrue(leave.title.equals(leaveTitle));
        assertTrue(leave.getLeaveTitle().equals(leaveTitle) && leave.getEndTime().equals(end) && leave.getStartTime().equals(start));
    }

    @When("the employee with id {string} add the leave titled {string} with start date set to day {int}, month {int}, year {int}, and end date set to day {int}, month {int}, year {int} to their list of activities")
    public void the_employee_with_id_add_the_leave_titled_with_start_date_set_to_day_month_year_and_end_date_set_to_day_month_year_to_their_list_of_activities(
            String employeeID, String leaveTitle, Integer startDay, Integer startMonth, Integer startYear,
            Integer endDay,
            Integer endMonth, Integer endYear) throws Exception {
        CustomCalendar start = new CustomCalendar(startYear, startMonth, startDay);
        CustomCalendar end = new CustomCalendar(endYear, endMonth, endDay);
        projectPlanner.getUser(employeeID).createLeave(start, end, leaveTitle);
    }

    @Then("the leave titled {string} is added to the employee with id {string} list of activities")
    public void the_leave_titled_is_added_to_the_employee_with_id_list_of_activities(String leaveTitle,
            String employeeID) {

                try {
                    assertTrue(projectPlanner.getUser(employeeID).getLeaveAll().stream().anyMatch(leave -> leave.getLeaveTitle().equals(leaveTitle)));
                } catch (Exception e) {
                    ErrorMessageHolder.setErrorMessage(e.getMessage());
                }
    }

    

    @Given("there is leave titled {string} with start date set to day {int}, month {int}, year {int}, and end date set to day {int}, month {int}, year {int}, in the employees with id {string} list of activities")
    public void there_is_leave_titled_with_start_date_set_to_day_month_year_and_end_date_set_to_day_month_year_in_the_employees_list_of_activities( String leaveTitle, Integer startDay, Integer startMonth, Integer startYear, Integer endDay,
            Integer endMonth, Integer endYear, String employeeID) throws Exception {
        CustomCalendar start = new CustomCalendar(startYear, startMonth, startDay);
        CustomCalendar end = new CustomCalendar(endYear, endMonth, endDay);
        User employee = projectPlanner.getUser(employeeID);
        employee.createLeave(start, end, leaveTitle);
        Leave leave = employee.getLeave(leaveTitle);
        assertTrue(leave.getEndTime().equals(end) && leave.getStartTime().equals(start) && leave.getLeaveTitle().equals(leaveTitle));
    }

    @When("the employee with id {string} changes the leave with title {string} start date to day {int}, month {int}, year {int} and the end date to day {int}, month {int}, year {int}")
    public void the_employee_with_id_changes_the_leave_with_title_start_date_to_day_month_year_and_the_end_date_to_day_month_year(String employeeID, String leaveTitle, Integer startDay, Integer startMonth, Integer startYear, Integer endDay,
            Integer endMonth, Integer endYear) throws Exception {
        User employee = projectPlanner.getUser(employeeID);
        CustomCalendar start = new CustomCalendar(startYear, startMonth, startDay);
        CustomCalendar end = new CustomCalendar(endYear, endMonth, endDay);
        Leave leave = employee.getLeave(leaveTitle);
        try {
            leave.changeDates(start, end);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }

    }

    @Then("the employee with id {string} leave with title {string} is changed to start date set to day {int}, month {int}, year {int} and end date set to day {int}, month {int}, year {int}")
    public void the_employee_with_id_leave_with_title_is_changed_to_start_date_set_to_day_month_year_and_end_date_set_to_day_month_year(
            String employeeID, String leaveTitle, Integer startDay, Integer startMonth, Integer startYear, Integer endDay,
            Integer endMonth, Integer endYear) throws Exception {

        User employee = projectPlanner.getUser(employeeID);
        Leave leave = employee.getLeave(leaveTitle);
        CustomCalendar start = new CustomCalendar(startYear, startMonth, startDay);
        CustomCalendar end = new CustomCalendar(endYear, endMonth, endDay);
        assertTrue(leave.getStartTime().getDate().equals(start.getDate()) && leave.getEndTime().getDate().equals(end.getDate()));
    }
    

    
    
}
