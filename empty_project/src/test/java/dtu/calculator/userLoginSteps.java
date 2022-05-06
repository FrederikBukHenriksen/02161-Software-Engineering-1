// package dtu.calculator;

// import static org.junit.jupiter.api.Assertions.*;

// import java.util.ArrayList;
// import io.cucumber.java.en.Given;
// import io.cucumber.java.en.Then;
// import io.cucumber.java.en.When;
// import io.cucumber.java.sl.In;

// public class userLoginSteps {
    
// final public CommonSteps commonSteps = new CommonSteps();
// final public ProjectPlanner projectPlanner;

// public userLoginSteps(CommonSteps commonSteps) {
// projectPlanner = commonSteps.projectPlanner;
// }
    
// // Frederik start

// @When("login user {string} with password {string}")
// public void login_user_with_password(String userId, String userPassword) {
// try {
// projectPlanner.logIn(userId, userPassword);
// } catch (Exception e) {
// ErrorMessageHolder.setErrorMessage(e.getMessage());
// }
// }

// @Then("user {string} is logged in")
// public void user_is_logged_in(String userId) throws Exception {
// User user = projectPlanner.getUser(userId);
// assertTrue(projectPlanner.getLoggedIn().equals(user));
// }

// @Then("user {string} is not logged in")
// public void user_is_not_logged_in(String userId) throws Exception {
// User user = projectPlanner.getUser(userId);
// assertFalse(projectPlanner.getLoggedIn().equals(user));
// }

// // Frederik Slut

// // @Given("the employee with id {string}")
// // public void the_employee_with_id(String initials) {
// // nameID = initials;
// // assertTrue(projectPlanner.getUsers().stream().anyMatch(user ->
// // user.initials.equalsIgnoreCase(initials)));
// // }

// // @Given("the employee is not logged in")
// // public void the_employee_is_not_logged_in() throws Exception {
// // if (projectPlanner.getLoggedIn() != null){
// //
// assertFalse(projectPlanner.getLoggedIn().initials.equals(projectPlanner.getUser(nameID).initials));
// // }
// // }

// // @Given("the employee's password is {string}")
// // public void the_employee_s_password_is(String pw) throws Exception {
// // projectPlanner.getUser(nameID).setPassword(pw);
// // assertTrue(projectPlanner.getUser(nameID).password.equals(pw));
// // }

// // @When("the employee with id {string} uses password {string}")
// // public void the_employee_with_id_uses_password(String initials, String pw)
// {
// // try {
// // projectPlanner.logIn(initials, pw);
// // } catch (Exception e) {
// // errorMessageHolder.setErrorMessage(e.getMessage());
// // }
// // }

// // @Then("the employee with id {string} is logged in")
// // public void the_employee_with_id_is_logged_in(String initials) {
// //
// assertTrue(projectPlanner.getLoggedIn().initials.equalsIgnoreCase(initials));
// // }

// // @Then("the employee with id {string} is not logged in")
// // public void the_employee_with_id_is_not_logged_in(String nameid) {
// // if (projectPlanner.getLoggedIn() != null){
// // assertFalse(projectPlanner.getLoggedIn().initials.equals(nameid));
// // }
// // }

// // @When("the employee with id {string} logs out")
// // public void the_employee_with_id_logs_out(String string) {
// // projectPlanner.logOut();
        
// // }

 
// }
