Feature: Register work time
  Description: User register work
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
    And create activity "Analysis" for project "2022-1"
    And add user "ANDR" to project "2022-1"
    And add user "GUST" to project "2022-1"
    And add user "ANDR" to activity "Analysis" in project "2022-1"

  Scenario: Register work time as activity user
    Given login user "ANDR"
    When user "ANDR" register work time from 2022 / 1 / 1 / 8:00 to 2022 / 1 / 1 / 16:00 to activity "Analysis" in project "2022-1"
    Then user "ANDR" does have a registration from 2022 / 1 / 1 8:00 to 2022 / 1 / 1 / 16:00 in activity "Analysis" in project "2022-1"

  Scenario: Register work time as project leader
    Given login user "FRED"
    When user "FRED" register work time from 2022 / 1 / 1 / 8:00 to 2022 / 1 / 1 / 16:00 to activity "Analysis" in project "2022-1"
    Then user "FRED" does have a registration from 2022 / 1 / 1 8:00 to 2022 / 1 / 1 / 16:00 in activity "Analysis" in project "2022-1"

  Scenario: Register work time as project user
    Given login user "GUST"
    When user "GUST" register work time from 2022 / 1 / 1 / 8:00 to 2022 / 1 / 1 / 16:00 to activity "Analysis" in project "2022-1"
    Then user "GUST" does have a registration from 2022 / 1 / 1 8:00 to 2022 / 1 / 1 / 16:00 in activity "Analysis" in project "2022-1"

  Scenario: Register work time as projectplanner user
    Given login user "NIKL"
    When user "NIKL" register work time from 2022 / 1 / 1 / 8:00 to 2022 / 1 / 1 / 16:00 to activity "Analysis" in project "2022-1"
    Then user "NIKL" does have a registration from 2022 / 1 / 1 8:00 to 2022 / 1 / 1 / 16:00 in activity "Analysis" in project "2022-1"

  Scenario: Register work time for another user as project leader
    Given login user "FRED"
    When user "ANDR" register work time from 2022 / 1 / 1 / 8:00 to 2022 / 1 / 1 / 16:00 to activity "Analysis" in project "2022-1"
    And user "ANDR" does have a registration from 2022 / 1 / 1 8:00 to 2022 / 1 / 1 / 16:00 in activity "Analysis" in project "2022-1"

  Scenario: Register work time for another user not possi ble
    Given login user "ANDR"
    When user "GUST" register work time from 2022 / 1 / 1 / 8:00 to 2022 / 1 / 1 / 16:00 to activity "Analysis" in project "2022-1"
    Then the error message "Cannot register work for other users" is given
    And user "GUST" does not have a registration from 2022 / 1 / 1 8:00 to 2022 / 1 / 1 / 16:00 in activity "Analysis" in project "2022-1"

Scenario: Register work time as administrator not possible
    Given login user "HUBE"
    When user "HUBE" register work time from 2022 / 1 / 1 / 8:00 to 2022 / 1 / 1 / 16:00 to activity "Analysis" in project "2022-1"
    Then the error message "Not allowed for administrator user" is given
    Then user "HUBE" does not have a registration from 2022 / 1 / 1 8:00 to 2022 / 1 / 1 / 16:00 in activity "Analysis" in project "2022-1"



