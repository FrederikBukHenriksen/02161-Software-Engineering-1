# Feature: Change an activity's time left estimate
# 	Description: An employee changes how long there is estimated until a task is finished
# 	Actors: Employee

# 	Background: Background Change an activity's time left estimate
# 		Given login user "HUBE"
# 		And create employee "FRED"
# 		And create employee "ANDR"
# 		And create employee "NIKA"
# 		And create a project titled "Software Development"
# 		And set user "FRED" as projectleader for project "2022-1"
# 		And login user "FRED"
# 		And add user "ANDR" to project "2022-1"
# 		And create activity "Analysis" for project "2022-1"
# 		And add user "ANDR" to activity "Analysis" for project "2022-1"

# 	# Main scenario
# 	Scenario: Change the time estimate of an activity
# 		Given login user "ANDR"
# 		When the employee "ANDR" changes the estimated time left for the activity titled "Analysis" in the project with id "2022-1" to 2.0 hours
# 		Then the estimated time left for the activity titled "Analysis" with id "2022-1" is set to 2.0 hours

# 	Scenario: Change the time estimate of an activity when you are not in the activity
# 		Given login user "NIKA"
# 		When the employee "NIKA" changes the estimated time left for the activity titled "Analysis" in the project with id "2022-1" to 2.0 hours
# 		Then the error message "User does not exist" is given

