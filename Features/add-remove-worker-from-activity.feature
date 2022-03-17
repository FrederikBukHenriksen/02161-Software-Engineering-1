Feature: Add and remove worker from an activity
    Description: An employee is added/removed to/from an activitiy
    Actors: Project manager

#Main use-case(s)
Scenario: Add activity employee
    Given the project manager is logged in
    And there is a project titled "Software Development"
    And the project contains an activity titled "Analysis"
    And there is an employee with firstName "Andreas", lastName "Bruun", and id "1"
    And the employee is not already on the activity's employee list
    When the activity employee is added to the activity employee list
    Then the activity employee with firstName "Andreas", lastName "Bruun", and id "1" is contained in the activity employee list

Scenario: Remove activity employee
    Given the project manager is logged in
    And there is a project titled "Software Development"
    And the project contains an activity titled "Analysis"
    And the activity contains an activitiy employee with firstName "Andreas", lastName "Bruun", and id "1"
    When the activity employee is removed from the activity employee list
    Then the activity employee with firstName "Andreas", lastName "Bruun", and id "1" is no longer conatined in the activity employee list

#Alternative use-case(s)
Scenario: Add activity employee when the project manager is not logged in
    Given the project manager is not logged in
    And there is a project titled "Software Development"
    And the project contains an activity titled "Analysis"
    And there is an employee with firstName "Andreas", lastName "Bruun", and id "1"
    And the employee is not already on the activity's employee list
    When the activity employee is added to the activity employee list
    Then the error message "Project manager login is required" is given

Scenario: Remove activity employee but the project manager is not signed in
    Given the project manager is logged in
    And there is a project titled "Software Development"
    And the project contains an activity titled "Analysis"
    And the activity contains an activitiy employee with firstName "Andreas", lastName "Bruun", and id "1"
    When the activity employee is removed from the activity employee list
    Then the error message "Project manager login is required" is given

Scenario: Add activity employee which already is assigned to the activity
    Given the project manager is logged in
    And there is a project titled "Software Development"
    And the project contains an activity titled "Analysis"
    And there is an employee with firstName "Andreas", lastName "Bruun", and id "1"
    And the employee is already on the activity's employee list
    When the activity employee is added to the activity employee list
    Then the error message "The employee is already assigned to the activity" is given






