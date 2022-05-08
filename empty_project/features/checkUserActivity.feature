Feature: Check an employees activities
    Description: An employee checks the activities of another employee


    Background:
    Given the date is year 2022 month 1 day 1
    And login user "HUBE"
    And create employee "ANDR"
    And create employee "NIKA"
    And create employee "FRED"
    And create a project titled "Software Development"
    And set user "FRED" as project leader for project "2022-1"
    And login user "FRED"
    And create activity "Analysis" for project "2022-1"
    And add user "NIKA" to the project "2022-1"
    And add user "NIKA" to activity "Analysis" for project "2022-1"


    Scenario: Check the activities assigned to another employee
        Given login user "ANDR"
        And user "NIKA" is assigned to activity "Analysis" for project "2022-1"
        Then the system displays the list of activities of the employee with id "NIKA"

#     # # Alternative scenario
    Scenario: Check the activities assigned to another employee who has no activities
        Given login user "NIKA"
        And user "ANDR" is not assigned any activity
        Then the number of activities for user "ANDR" is 0
    
    Scenario: Check the activities assigned to another employee who does not exist
        Given login user "NIKA"
        And user "MUHA" does not exist
        And user "MUHA" is not assigned any activity
        Then the error message "User does not exist" is given

