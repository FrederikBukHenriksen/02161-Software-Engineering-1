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
    # Scenario: change vacation/course
    #     Given that an employee with id "andr" is logged in
    #     And the employee has an activity titled "vacation" in their list of activities
    #     And that the activity is set to start on day "1", month "1", year "2022"
    #     And that the activity is set to end day "1", month "2", year "2022"
    #     And that the activity's new start date is set to day "6", month "9", year "2022"
    #     And that the activity's new end date is set to day "9", month "11", year "2022"
    #     When the employee changes the activity's date
    #     Then the activity's dates are changed




