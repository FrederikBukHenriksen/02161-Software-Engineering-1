Feature: Change activity budgeted time
	Description: An employee changes the budgeted time of an activity given in hours
	Actors: Employee

	# Main scenario
	Scenario: Change the budgeted time of an activity
		Given an employee selects <activity>
		And an employee changes the budgeted time of the activity to <newTime>
		When the confirmation button is pressed
		Then the budgeted time of the activity is changed to <newTime>

