Feature: User login
    Description: User login
    Actor: Administrator and employee

    Background: Background user login
        Given the date is year 2022 month 1 day 1
        And login user "HUBE"
        And create employee "ANDR"

    Scenario: User login
        When login user "andr" with password "01234"
        Then user "andr" is logged in

    Scenario: User login wrong password
        When login user "andr" with password "kodeord"
        Then the error message "Wrong id or password" is given
        Then user "andr" is not logged in

    Scenario: User login initials for a user which does not exist
        When login user "fred" with password "01234"
        Then the error message "Wrong id or password" is given
