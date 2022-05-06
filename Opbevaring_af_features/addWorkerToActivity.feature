#skrevet af Frederik Buk Henriksen
Feature: Add employee to activity
    Description: An employee is added to an activity
    Actors: Project leader

Background: Background add user to activity
    Given that there exists a project titled "Software Development" with id "2022-1"
    And JUNIT create an employee "fred"
    And JUNIT create an employee "andr"
    And set user "fred" as project leader for project "2022-1"


# Main case
Scenario: Add  employee to activity
    Given that the project leader of the project "2022-1" is logged in
    And the activity "Analysis" is added to the project "2022-1"
    And the user "andr" is not in the activity "Analysis" in the project "2022-1"
    When the user "andr" is added to the activity "Analysis" in the project "2022-1"
    Then the user "andr" is in the activity "Analysis" in the project "2022-1"


    # Given that there exists a project titled "Software Development" with id "2022-1"
    # And the user "fred" for the project "2022-1" is logged in
    # And the project with id "2022-1" contains an activity titled "Analysis"
    # And there is an employee with id "andr"
    # And the employee with id "andr" is not already on the activity with the title "Analysis" in the project with id "2022-1" list of employees
    # When the employee with id "andr" is added to the list of employees for the activity with the title "Analysis" in the project with id "2022-1"
    # Then employee with id "andr" is added to the list of employees for the activity with the title "Analysis" in the project with id "2022-1"

    # # Alternative use-case(s)  
    Scenario: Add an employee to an activity when the project leader is not logged in
        Given that there exists a project titled "Software Development" with id "2022-1"
        And that the project leader of the project "2022-1" is not logged in
        And the project with id "2022-1" contains an activity titled "Analysis"
        And there is an employee with id "andr"
        And the employee with id "andr" is not already on the activity with the title "Analysis" in the project with id "2022-1" list of employees
        When the employee with id "andr" is added to the list of employees for the activity with the title "Analysis" in the project with id "2022-1"
        Then the error message "Project leader login is required" is given

    # # Alternative use-case
    Scenario: Add an employee to an activity which already they are already assigned to
        Given that there exists a project titled "Software Development" with id "2022-1"
        And the user "fred" for the project "2022-1" is logged in
        And the project with id "2022-1" contains an activity titled "Analysis"
        And there is an employee with id "andr"
        And the employee with id "andr" is already on the activity with the title "Analysis" in the project with id "2022-1" list of employees
        When the employee with id "andr" is added to the list of employees for the activity with the title "Analysis" in the project with id "2022-1"
        Then the error message "The employee is already assigned to the activity" is given
