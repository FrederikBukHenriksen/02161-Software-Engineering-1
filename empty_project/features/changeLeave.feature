#Skrevet af Muhammad Muneeb Farooq
Feature: add/change vacation or course 
    Description: allow an employee to add or change planned vacation or course
    Actors: employee

    Background:
    Given the date is year 2022 month 1 day 1
    And login user "HUBE"
    And create employee "ANDR"
    And login user "ANDR"

    # Main use-case(s)
    Scenario: add leave
        Given there is leave titled "vacation" with start date set to day 1, month 1, year 2022, and end date set to day 1, month 2, year 2022
        When the employee with id "ANDR" add the leave titled "vacation" with start date set to day 1, month 1, year 2022, and end date set to day 1, month 2, year 2022 to their list of activities
        Then the leave titled "vacation" is added to the employee with id "ANDR" list of activities

    # Alternative scenario
    Scenario: change leave
        Given there is leave titled "vacation" with start date set to day 1, month 1, year 2022, and end date set to day 1, month 2, year 2022, in the employees with id "ANDR" list of activities
        When the employee with id "ANDR" changes the leave with title "vacation" start date to day 2, month 1, year 2022 and the end date to day 2, month 2, year 2022
        Then the employee with id "ANDR" leave with title "vacation" is changed to start date set to day 2, month 1, year 2022 and end date set to day 2, month 2, year 2022


    Scenario: change leave to illegal date
        Given there is leave titled "vacation" with start date set to day 1, month 1, year 2022, and end date set to day 1, month 2, year 2022, in the employees with id "ANDR" list of activities
        When the employee with id "ANDR" changes the leave with title "vacation" start date to day 31, month 6, year 2022 and the end date to day 1, month 1, year 2022
        Then the error message "Start time must be before end time" is given

