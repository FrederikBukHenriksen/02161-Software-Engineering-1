import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class createAndChangeActiveties {
	
	Project project = new Project();
	
	@Given("an employee selects {Activety}")
	public void selectEmployee(Activety activety) {
		project.setSelectedActivety(activety);
	}
	
	@And("an employee changes the time of the activety to {int}")
	public void newTimeisSpecified(int newTime) {
		project.getSelectedActivety().newTime = newTime;
	}
	
	@When("the confirmation button is pressed")
	public void confirmationButtonIsPressed() {
		project.getSelectedActivety().time = project.getSelectedActivety().newTime;
	}
	
	@Then("the time of {Activety} is changed to {int}")
	public void setNewActivetyTime(int newTime) {
		assertEquals((int) project.getSelectedActivety().time, (int) newTime);
	}
	
	
	@Given("an employee selects {Employee}")
	public void selectEmployee(Employee employee) {
		project.setSelectedEmployee(employee);
	}
	
	@And("{Employee} has a list of activeties of positive length")
	public void checkEmployeeHasActiveties(Employee employee) {
		assertTrue(project.getSelectedEmployee().activeties.length() >= 1);
	}
	
	@Then("the system displays the list of activeties of {Employee}")
	public void returnEmployeeActiveties(Employee employee) {
		display(project.getSelectedEmployee().activeties);
	}
	
	
	@Given("an employee selects {Employee}")
	public void selectEmployee(Employee employee) {
		project.setSelectedEmployee(employee);
	}
	
	@And("{Employee} has a list of activeties of no length")
	public void checkEmployeeHasActiveties(Employee employee) {
		assertEquals(project.getSelectedEmployee().activeties.length(), 0);
	}
	
	@Then("the system displays that {Employee} has no assigned activeties")
	public void noActivetiesFoundMessage(Employee employee) {
		display(employee.initials + " has no assigned activeties");
	}
}
