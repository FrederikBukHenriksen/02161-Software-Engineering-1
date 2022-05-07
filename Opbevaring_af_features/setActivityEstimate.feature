Feature: Change an activity's time left estimate
	Description: An employee changes how long there is estimated until a task is finished
	Actors: Employee

	# Main scenario
	Scenario: Change the time estimate of an activity
		Given that there exists a project titled "Software Development" with id "2022-1"
		And there is an employee with id "andr" who is in the list of employees for the project with id "2022-1"
		And the employee with id "andr" is in the list of employees for the activity titled "Analysis" in the project with id "2022-1"
		When the employee changes the estimated time left for the activity titled "Analysis" in the project with id "2022-1" to 2.0 hours
		Then the estimated time left for the activity titled "Analysis" with id "2022-1" is set to 2.0 hours

