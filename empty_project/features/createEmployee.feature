Feature: Create employee
    Description: Create employee in the projectplanner
    Actors: Administrator

    Background: Background create employee
        Given login user "HUBE"
        And create employee "FRED"

    #Main use-case(s)
    Scenario: Create employee
        When create employee "ANDR"
        Then user "ANDR" is in the projectplanner

    Scenario: Create employee when it already exists in projectplanner
        When create employee "ANDR"
        And user "ANDR" is in the projectplanner
        When create employee "ANDR"
        Then the error message "Initals are already in use" is given
        Then user "andr" only appears once in the projectplanner

    Scenario: Create employee with more than four initials
        When create employee "ANDREAS"
        Then the error message "Initials must be four letters" is given
        And user "ANDREAS" is not in the projectplanner

    Scenario: Create employee with less than four initials
        When create employee "AND"
        Then the error message "Initials must be four letters" is given
        And user "AND" is not in the projectplanner

    Scenario: Create employee when the administrator is not logged in
        Given login user "FRED"
        When create employee "ANDR"
        Then the error message "Administrator login is required" is given
        And user "ANDR" is not in the projectplanner






