Feature: Logout functionality
    Scenario Outline: Success Logout
        Given user is on dashboard page
        When user click menu icon
        Then sidebar is opened
        When user click logout menu
        Then user redirect to login page
