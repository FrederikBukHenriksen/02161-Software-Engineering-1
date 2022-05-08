#skrevet af Frederik Buk Henriksen
Feature: Add employee to activity
    Description: An employee is added to an activity
    Actors: Project leader

Background: Background add user to activity
    Given the date is year 2022 month 1 day 1
    And login user "HUBE"
    And create a project titled "Software Development"
    And create employee "FRED"
    And create employee "ANDR"
    And create employee "GUST"
    And create employee "NIKL"
    And set user "FRED" as project leader for project "2022-1"
    And login user "FRED"
    And add user "FRED" to project "2022-1"
    And add user "ANDR" to project "2022-1"
    And add user "GUST" to project "2022-1"
    And create activity "Analysis" for project "2022-1"


# Main case
Scenario: Add employee to activity
    Given login user "FRED"
    When add user "ANDR" to activity "Analysis" in project "2022-1"
    Then user "ANDR" is in activity "Analysis" in project "2022-1"

Scenario: Add employee which does not excist
    Given login user "FRED"
    When add user "MUHA" to activity "Analysis" in project "2022-1"
    Then the error message "User does not exist" is given

Scenario: Add employee to activity which already is in the activity
    Given login user "FRED"
    And add user "ANDR" to activity "Analysis" in project "2022-1"
    And user "ANDR" is in activity "Analysis" in project "2022-1"
    When add user "ANDR" to activity "Analysis" in project "2022-1"
    Then the error message "User is already in the activity" is given
    And user "ANDR" only appears once in activity "Analysis" in project "2022-1"

Scenario: Add employee from projectplanner to activity not possible
    Given login user "FRED"
    When add user "NIKL" to activity "Analysis" in project "2022-1"
    Then the error message "User is not in the project" is given
    And user "NIKL" is not in activity "Analysis" in project "2022-1"

Scenario: Add employee to activity not possible when administrator is logged in
    Given login user "HUBE"
    When add user "ANDR" to activity "Analysis" in project "2022-1"
    Then the error message "Project leader login is required" is given
    And user "ANDR" is not in activity "Analysis" in project "2022-1"

Scenario: Add employee to activity not possible when project user is logged in
    Given login user "ANDR"
    When add user "GUST" to activity "Analysis" in project "2022-1"
    Then the error message "Project leader login is required" is given
    And user "GUST" is not in activity "Analysis" in project "2022-1"

Scenario: Add employee to activity not possible when project user is logged in
    Given login user "ANDR"
    When add user "NIKL" to activity "Analysis" in project "2022-1"
    Then the error message "Project leader login is required" is given
    And user "GUST" is not in activity "Analysis" in project "2022-1"

Scenario: Add administrator to activity not possible
    Given login user "FRED"
    When add user "HUBE" to activity "Analysis" in project "2022-1"
    Then the error message "Not allowed for administrator user" is given
    And user "HUBE" is not in activity "Analysis" in project "2022-1"