package dtu.calculator;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Optional;

import dtu.calculator.ProjectPlanner;

public class removeEmployeeSteps {

    ProjectPlanner projectPlanner;
    ErrorMessageHolder errorMessageHolder;

    public removeEmployeeSteps(ProjectPlanner projectplanner, ErrorMessageHolder errorMessageHolder) {
        this.projectPlanner = projectplanner;
        this.errorMessageHolder = errorMessageHolder;
    }

    @When("the employee {string} is removed from the list")
    public void the_employee_is_removed_from_the_list(String initials) {
        try {
            User user = projectPlanner.getUser(initials);
            try {
                projectPlanner.removeEmployee(user);
            } catch (Exception e) {
                errorMessageHolder.setErrorMessage(e.getMessage());
            }
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
}
