Feature: Change an activity's time left estimate
	Description: An employee changes how long there is estimated until a task is finished
	Actors: Employee

	# Main scenario
	Scenario: Change the time estimate of an activity
		Given that an employee with id "andr" is logged in
		And the employee is in the list of employees for the project titled "Software Development" with id "22001"
		And the employee is in the list of employees for the activity titled "Analysis" 
		And the employee changes the estimated time left for the activity to "2" hours
		When the employee changes the estimated time left
		Then the estimated time left for the activity is set to "2" hours

