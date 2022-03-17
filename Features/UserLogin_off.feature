Feature: User login
    Description: The user can login and logout from the site
    Actor: User
    
Scenario: User can login
    Given the user with IDnum "213432" is not logged in
    And the password is "Bruun"
    Then the user with IDnum "213432" login succeeds
    And the user with IDnum "213432" is logged in
    
Scenario: User has the wrong password
    Given the user with IDnum "213432" is not logged in
    And the password is "wrong password"
    Then the user with IDnum"213432" login fails
    And the user with IDnum "213432" is not logged in
    And the error message "Password or ID is wrong" is given

Scenario: User can logout
    Given the user with IDnum "213432" is logged in
    And the user with IDnum "213432" will logout
    Then the user with IDnum "213432" logout succeeds
    
    
Scenario: User has the wrong password
    Given the user with IDnum "213432" is not logged in
    And the password is "wrong password"
    Then the user with IDnum"213432" login fails
    And the user with IDnum "213432" is not logged in
    And the error message "Password or ID is wrong" is given
