Feature: Login functionality
    Scenario Outline: Success Login
        Given user is on Saucedemo login page
        When user input <username> as username
        When user input <password> as password
        And user click login button
        Then user redirect to dashboard

        Examples:
            | username      | password     |
            | standard_user | secret_sauce |



    Scenario Outline: Error Login
        Given user is on Saucedemo login page
        When user input <username> as username
        When user input <password> as password
        And user click login button
        Then user get error message

        Examples:
            | username   | password    |
            | error_user | saos_sambal |
