Feature: Add and remove worker from an activity

Scenario: Add projectworker
Given the project manager is logged in
And there is a registered project called "Software Development"
And the project called "Software Development" contains an activity called "Analysis"
When the projectworker called "Frederik" is added to the activity called "Analysis"
Then the projectworker "Frederik" is a part of the activity called "Analysis"

Scenario: Add projectworker but the project manager is not signed in
Given the project manager is not logged in
And there is a registered project called "Software Development"
And the project called "Software Development" contains an activity called "Analysis"
When When the projectworker called "Frederik" is added to the activity called "Analysis"
Then the error message "Project manager login is required" is given



###REMOVE
Scenario: Remove projectworker
Given the project manager is logged in
And there is a registered project called "Software Development"
And the project called "Software Development" contains an activity called "Analysis"
And the projectworker called "Frederik" is a part of to the activity called "Analysis"
When the projectworker called "Frederik" is removed from the activity "Analysis"
Then the projectworker "Frederik" is not a part of the activity called "Analysis"

Scenario: Remove projectworker but the project manager is not signed in
Given the project manager is not logged in
And there is a registered project called "Software Development"
And the project called "Software Development" contains an activity called "Analysis"
And the worprojectworkerker called "Frederik" is a part of to the activity called "Analysis"
When the projectworker called "Frederik" is removed from the activity "Analysis"
Then the error message "Project manager login is required" is given

Scenario: Remove projectworker but the projectworker is not found
Given the project manager is logged in
And there is a registered project called "Software Development"
And the project called "Software Development" contains an activity called "Analysis"
And the projectworker called "Frederik" is a part of to the activity called "Analysis"
When the projectworker called "Gustav" is removed from the activity "Analysis"
Then the error message "The projectworker could not be found among the activitiy's assigned projectworker" is given







