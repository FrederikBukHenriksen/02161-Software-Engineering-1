package dtu.calculator;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.GregorianCalendar;

public class registerTime {
    final public CommonSteps commonSteps = new CommonSteps();
    final public ProjectPlanner projectPlanner;

    public registerTime(CommonSteps commonSteps) {
        projectPlanner = commonSteps.projectPlanner;
    }

    // Frederik Start
    // 2022 / 1 / 1 - 8:00
    @When("user {string} register work time from {int} \\/ {int} \\/ {int} \\/ {int}:{int} to {int} \\/ {int} \\/ {int} \\/ {int}:{int} to activity {string} in project {string}")
    public void user_register_time_from_to_to_activity_in_project(String userInitials, Integer yearStart,
            Integer monthStart,
            Integer dateStart, Integer hourStart, Integer minuteStart, Integer yearEnd, Integer monthEnd,
            Integer dateEnd,
            Integer hourEnd,
            Integer minuteEnd, String activityTitle, String projectId) {

        try {
            User user = projectPlanner.getUser(userInitials);
            Project project = projectPlanner.getProject(projectId);
            Activity activity = project.getActivity(activityTitle);
            user.registerWork(
                    new CustomCalendar(yearStart, monthStart, dateStart, hourStart, minuteStart),
                    new CustomCalendar(yearEnd, monthEnd, dateEnd, hourEnd, minuteEnd),
                    activity);
        } catch (Exception e) {
            ErrorMessageHolder.setErrorMessage(e.getMessage());
        }

    }

    @Then("user {string} does have a registration from {int} \\/ {int} \\/ {int} {int}:{int} to {int} \\/ {int} \\/ {int} \\/ {int}:{int} in activity {string} in project {string}")
    public void user_does_have_a_registration_from_to_in_activity_in_project(String userInitials, Integer yearStart,
            Integer monthStart,
            Integer dateStart, Integer hourStart, Integer minuteStart, Integer yearEnd,
            Integer monthEnd,
            Integer dateEnd,
            Integer hourEnd,
            Integer minuteEnd, String activityTitle, String projectId) throws Exception {

        User user = projectPlanner.getUser(userInitials);
        Project project = projectPlanner.getProject(projectId);
        Activity activity = project.getActivity(activityTitle);
        boolean found = false;
        for (Work work : user.getWorkRegistrations()) {
            if (work.getActivity().equals(activity)) {
                CustomCalendar startTimeCheck = new CustomCalendar(yearStart, monthStart, dateStart, hourStart,
                        minuteStart);
                if (work.getStartTime().equals(startTimeCheck)) {
                    CustomCalendar endTimeCheck = new CustomCalendar(yearEnd, monthEnd, dateEnd, hourEnd,
                            minuteEnd);
                    if (work.getEndTime().equals(endTimeCheck)) {
                        found = true;
                    }
                }
            }

        }
        assertTrue(found==true);
    }

    @Then("user {string} does not have a registration from {int} \\/ {int} \\/ {int} {int}:{int} to {int} \\/ {int} \\/ {int} \\/ {int}:{int} in activity {string} in project {string}")
    public void user_does_not_have_a_registration_from_to_in_activity_in_project(String userInitials, Integer yearStart,
            Integer monthStart,
            Integer dateStart, Integer hourStart, Integer minuteStart, Integer yearEnd,
            Integer monthEnd,
            Integer dateEnd,
            Integer hourEnd,
            Integer minuteEnd, String activityTitle, String projectId) throws Exception {

        User user = projectPlanner.getUser(userInitials);
        Project project = projectPlanner.getProject(projectId);
        Activity activity = project.getActivity(activityTitle);
        boolean found = false;
        for (Work work : user.getWorkRegistrations()) {
            if (work.getActivity().equals(activity)) {
                CustomCalendar startTimeCheck = new CustomCalendar(yearStart, monthStart, dateStart, hourStart,
                        minuteStart);
                if (work.getStartTime().equals(startTimeCheck)) {
                    CustomCalendar endTimeCheck = new CustomCalendar(yearEnd, monthEnd, dateEnd, hourEnd,
                            minuteEnd);
                    if (work.getEndTime().equals(endTimeCheck)) {
                        found = true;
                    }
                }
            }

        }
        assertFalse(found == true);
    }

    // Frederik End

}

