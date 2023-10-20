Feature: Checkout functionality

  Scenario Outline: User success checkout
    Given user is on cart detail page
    When user click checkout button
    Then user redirect to checkout page
    When user input <firstname> as first name
    When user input <lastname> as last name
    When user input <postalcode> as postal code
    When user click continue button
    Then user redirect to checkout overview page
    When user click finish button
    Then checkout is completed

    Examples: 
      | firstname | lastname | postalcode |
      | Akhmad    | Muntako  |     123456 |

  Scenario Outline: User error checkout
    Given user is on cart detail page
    When user click checkout button
    Then user redirect to checkout page
    When user input <firstname> as first name
    When user input <postalcode> as postal code
    When user click continue button
    Then user get checkout error message

    Examples: 
      | firstname | postalcode |
      | Akhmad    |     123456 |
