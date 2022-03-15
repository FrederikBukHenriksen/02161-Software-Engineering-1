Feature: Change activety time estimate
	Description: An employee changes the time estimate of an activety
	Actors: Employee

	# Main scenario
	Scenario: Change the time estimate of an activety
		Given an employee selects an activety
		When an employee changes the time estimate of the activety
		Then the time estimate of the activety is changed

#################################################################################

Feature: Check employee activeties
	Description: An employee checks the activeties of another employee
	Actors: Employee

	# Main scenario
	Scenario: Check the activeties assigned to another employee
		Given an employee selects another employee
		And the selected employee has assigned activeties
		Then the system returns a list of the activeties of the selected employee

	# Alternative scenario
	Scenario: The chosen employee has no assigned activeties
		Given an employee selects another employee
		And the selected employee has no assigned activeties
		Then the system displays that the selected employee has no assigned activeties