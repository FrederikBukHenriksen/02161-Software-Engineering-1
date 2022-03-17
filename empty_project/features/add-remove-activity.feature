Feature: Add and remove activity to a project
###ADD
#MAIN
Scenario: Add activity
Given the project manager is logged in
And there is a registered project called "Software Development"
When the activity called "Analysis" is added to "Software Development"
Then the project called "Software Development" contains an activity called "Analysis" 

#ALT: Project manager is *NOT* logged in
Scenario: Add activity but project manager is not signed in
Given the project manager is not logged in
And there is a registered project called "Software Development"
When the activity called "Analysis" is added to "Software Development"
Then the error message "Project manager login is required" is given



###REMOVE
Scenario: Remove activity
Given the project manager is logged in
And there is a registered project called "Software Development"
And the project called "Software Development" contains an activity called "Analysis"
When the activity called "Analysis" is removed from the project called "Software Development"
Then the project called "Software Development" no longer contains an activity called "Analysis"

#ALT: Project manager is *NOT* logged in
Scenario: Remove activity but project manager is not signed in
Given the project manager is not logged in
And there is a registered project called "Software Development"
And the project called "Software Development" contains an activity called "Analysis"
When the activity called "Analysis" is removed from the project called "Software Development"
Then the error message "Project manager login is required" is given

#ALT: Activity does not excist/Project has no activities
Scenario: Remove activity but activity is not found
Given the project manager is logged in
And there is a registered project called "Software Development"
And the project called "Software Development" contains an activity called "Analysis"
When the activity called "Conclusion" is removed from the project called "Software Development"
Then the error message "The activity could not be found among the project's activities" is given
