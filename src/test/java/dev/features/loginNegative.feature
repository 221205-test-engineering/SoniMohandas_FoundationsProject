Feature: Login

  Background:
    Given The employee is on the login page

  Scenario Outline: Login Correct Username Wrong Password
    When The employee types in "<username>" into the username input
    When The employee types in "<password>" into the password input
    When The employee clicks on the login button
    Then The employee should see an alert saying they have the wrong password
    Examples:
      | username | password |
      | g8tor    | chomp!!  |

  Scenario Outline: Login Wrong Username Correct Password
    When The employee types in "<username>" into the username input
    When The employee types in "<password>" into the password input
    When The employee clicks on the login button
    Then The employee should see an alert saying no user with that username found
    Examples:
      |  username  | password  |
      | sicEmDawgs | natchamps |

