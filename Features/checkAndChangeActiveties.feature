Feature: Change activety time estimate
# Description: An employee changes the time estimate of an activety
# Actors: Employee

	# Main scenario
	Scenario: Change the time estimate of an activety
		Given an employee selects <activety>
		And an employee changes the time of the activety to <newTime>
		When the confirmation button is pressed
		Then the time of <activety> is changed to <newTime>

#################################################################################

Feature: Check employee activeties
# Description: An employee checks the activeties of another employee
# Actors: Employee

	# Main scenario
	Scenario: Check the activeties assigned to another employee
		Given an employee selects <other_employee>
		And the <other_employee> has a list of activeties of positive length
		Then the system displays the list of activeties of <other_employee>

	# Alternative scenario
	Scenario: The chosen employee has no assigned activeties
		Given an employee selects <other_employee>
		And the <other_employee> has <other_employee.activeties> of no length
		Then the system displays that the <other_employee> has no assigned activeties

