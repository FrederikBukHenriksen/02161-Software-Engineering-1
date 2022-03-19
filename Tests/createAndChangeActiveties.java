import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class createAndChangeActiveties {
	
	Project project = new Project();
	
	@Given("an employee selects {Activity}")
	public void selectActivity(Ativity activity) {
		project.setSelectedActivity(activity);
	}
	
	@And("an employee changes the budgeted time of the activity to {int}")
	public void specifyNewTime(int newTime) {
		project.getSelectedActivity().newTime = newTime;
	}
	
	@When("the confirmation button is pressed")
	public void confirmationButtonIsPressed() {
		project.getSelectedActivity().time = project.getSelecteAactivity().newTime;
	}
	
	@Then("the budgeted time of the activity is changed to {int}")
	public void setNewActivityTime(int newTime) {
		assertEquals((int) project.getSelectedActivity().time, (int) newTime);
	}
	
	
	@Given("an employee selects {Employee}")
	public void selectEmployee(Employee employee) {
		project.setSelectedEmployee(employee);
	}
	
	@And("the selected employee has a list of activeties of positive length")
	public void checkEmployeeHasActiveties() {
		assertTrue(project.getSelectedEmployee().activeties.length() >= 1);
	}
	
	@Then("the system displays the list of activeties of the selected employee")
	public void returnEmployeeActiveties() {
		display(project.getSelectedEmployee().activeties);
	}
	
	
	@Given("an employee selects {Employee}")
	public void selectEmployee(Employee employee) {
		project.setSelectedEmployee(employee);
	}
	
	@And("the selected employee has a list of activeties of no length")
	public void checkEmployeeHasActiveties() {
		assertEquals(project.getSelectedEmployee().activeties.length(), 0);
	}
	
	@Then("the system displays that selected employee has no assigned activeties")
	public void noActivetiesFoundMessage() {
		display(project.getSelectedEmployee().initials + " has no assigned activeties");
	}
}
