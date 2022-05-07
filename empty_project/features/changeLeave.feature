#Skrevet af Muhammad Muneeb Farooq
Feature: add/change vacation or course 
    Description: allow an employee to add or change planned vacation or course
    Actors: employee

    # Main use-case(s)
    Scenario: add vacation/course
        Given that employee with id "andr" is logged in
        And there is leave titled "vacation" with start date set to day 1, month 1, year 2022, and end date set to day 1, month 2, year 2022
        When the employee with id "andr" add the leave titled "vacation" with start date set to day 1, month 1, year 2022, and end date set to day 1, month 2, year 2022 to their list of activities
        Then the leave titled "vacation" is added to the employee with id "andr" list of activities

    # Alternative scenario
    Scenario: change vacation/course
        Given that employee with id "andr" is logged in
        And the employee with id "andr" has a leave titled "vacation" with start date set to day 1, month 1, year 2022, and end date set to day 1, month 2, year 2022 in their list of activities
        When the employee with id "andr" changes the leave titled "vacation" start date to day 1, month 1, year 2023, and end date to day 1, month 3, year 2023
        Then the leave titled "vacation" is changed to start date set to day 1, month 1, year 2023, and end date set to day 1, month 3, year 2023 in the employee with id "andr" list of activities




