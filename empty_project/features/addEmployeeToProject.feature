Feature: Add employee to project
Description: Add employee from projectplanner to a project
Actors: Project leader

  Background: Background add employee to project
    Given the date is year 2022 month 1 day 1
    And login user "HUBE"
    And create a project titled "Software Development"
    And create employee "FRED"
    And create employee "ANDR"
    And create employee "GUST"
    And set user "FRED" as project leader for project "2022-1"

  Scenario: Add an employee to project
    Given login user "FRED"
    When add user "ANDR" to project "2022-1"
    Then the user "ANDR" is in the project "2022-1"

  Scenario: Project leader adds itself to the project
    Given login user "FRED"
    And the user "FRED" is not in the project "2022-1"
    When add user "FRED" to project "2022-1"
    Then the user "FRED" is in the project "2022-1"

  Scenario: Add employee which does not exist
    Given login user "FRED"
    When add user "NIKL" to project "2022-1"
    Then the error message "User does not exist" is given

  Scenario: Add employee to a project which does not exist
    Given login user "FRED"
    When add user "ANDR" to project "2022-2"
    Then the error message "Project does not exist" is given

  Scenario: Add employee to a project which is already added
    Given login user "FRED"
    And add user "ANDR" to project "2022-1"
    And the user "ANDR" is in the project "2022-1"
    When add user "ANDR" to project "2022-1"
    Then the error message "User is already in the project" is given
    And the user "ANDR" is only once in the project "2022-1"

  Scenario: Add employee not possible when administrator is logged in
    Given login user "HUBE"
    When add user "ANDR" to project "2022-1"
    Then the error message "Project leader login is required" is given
    And the user "ANDR" is not in the project "2022-1"

  Scenario: Add employee not possible when project user is logged in
    Given login user "ANDR"
    When add user "GUST" to project "2022-1"
    Then the error message "Project leader login is required" is given
    And the user "GUST" is not in the project "2022-1"