import static org.junit.Assert.assertEquals;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class createProjectSteps {

	Project project = new Project();

	@Given("the first input is {String}")
	public void theFirstInputIs(String project_name) {
		project.setName(project_name);
	}

	@And("the second input is {String}")
	public void theSecondInputIs(String project_start_date) {
		project.setStartDate(project_start_date);
	}

	@And("the third input is {String}")
	public void theThirdInputIs(String project_end_date) {
		project.setEndDate(project_end_date);
	}

	@When("the add button is pressed")
	public void theAddButtonIsPressed() {
		project.add();
	}

	// Should test if the project is in the Project ArrayList. However, i'm not sure
	// yet if this is saved in an Arraylist....
	@Then("{String} is shown on the display.")
	public void theIsShown(Integer number) {
		for(int i = Calender.ArrayList.length; i < calender.Arraylist.length + 1; i++) {
			if (project.getId() == Calender.ArrayList.get(i)) {
				// assert true. 
			}

		}

	}
