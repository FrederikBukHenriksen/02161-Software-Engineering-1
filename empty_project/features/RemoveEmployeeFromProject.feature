Feature: Remove employee from project
  Description: Remove employee from project
  Actors: Project leader

  Background: Background remove employee from project
    Given the date is year 2022 month 1 day 1
    And login user "HUBE"
    And create a project titled "Software Development"
    And create employee "FRED"
    And create employee "ANDR"
    And create employee "GUST"
    And create employee "NIKL"
    And set user "FRED" as project leader for project "2022-1"
    And login user "FRED"
    And add user "ANDR" to project "2022-1"
    And add user "GUST" to project "2022-1"

  Scenario: Remove employee from project
    Given login user "FRED"
    When remove user "ANDR" from project "2022-1"
    Then the user "ANDR" is not in the project "2022-1"

  Scenario: Project leader removes itself from the project
    Given login user "FRED"
    And add user "FRED" to project "2022-1"
    And the user "FRED" is in the project "2022-1"
    When remove user "FRED" from project "2022-1"
    Then the user "FRED" is not in the project "2022-1"

  Scenario: Remove employee from project which is not in the project
    Given login user "FRED"
    When remove user "NIKL" from project "2022-1"
    Then the error message "User is not in the project" is given
    Then the user "NIKL" is not in the project "2022-1"

  Scenario: Remove employee from project which does not exist
    Given login user "FRED"
    When remove user "MUHA" from project "2022-1"
    Then the error message "User does not exist" is given

  Scenario: Remove employee from project from which the project does not exist
    Given login user "FRED"
    When remove user "ANDR" from project "2022-2"
    Then the error message "Project does not exist" is given

  Scenario: Remove employee from the project not possible when administrator is logged in
    Given login user "HUBE"
    When remove user "ANDR" from project "2022-1"
    Then the error message "Project leader login is required" is given
    And the user "ANDR" is in the project "2022-1"

  Scenario: Remove employee from the project not possible when project user is logged in
    Given login user "ANDR"
    When remove user "GUST" from project "2022-1"
    Then the error message "Project leader login is required" is given
    And the user "GUST" is in the project "2022-1"

  Scenario: Employee remove itself from project not possible when project user is logged in
    Given login user "ANDR"
    When remove user "ANDR" from project "2022-1"
    Then the error message "Project leader login is required" is given
    And the user "ANDR" is in the project "2022-1"