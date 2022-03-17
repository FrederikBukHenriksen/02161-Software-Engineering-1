Feature: Change activity time estimate
	Description: An employee changes the time estimate of an activity
	Actors: Employee

	# Main scenario
	Scenario: Change the time estimate of an activity
		Given an employee selects <activity>
		And an employee changes the time of the activity to <newTime>
		When the confirmation button is pressed
		Then the time of the activity is changed to <newTime>

