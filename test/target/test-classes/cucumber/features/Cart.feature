Feature: Cart functionality
    Scenario Outline: Cart badge value update when user add item
        Given user is on Saucedemo dashboard page
        When user views inventories
        When user click button add to cart <count> time/times
        Then cart badge label value is <count>
        And close browser

        Examples:
            | count |
            | 1     |

    Scenario Outline: Item in detail page update when user add item
        Given user is on Saucedemo dashboard page
        When user views inventories
        When user click button add to cart <count> time/times
        When user click shopping cart icon
        Then user redirect to cart detail page
        And item count in cart detail page is <count>
        And close browser

        Examples:
            | count |
            | 2     |

    Scenario Outline: Item in detail page update when user delete item
        Given user is on Saucedemo dashboard page
        When user views inventories
        When user click button add to cart <add> time/times
        When user click shopping cart icon
        Then user redirect to cart detail page
        And item count in cart detail page is <add>
        When user click remove button <remove> time/times
        Then item count in cart detail page is <result>
        And item count in cart detail page is <result>
        And close browser

        Examples:
            | add | remove | result |
            | 2   | 1      | 1      |

    Scenario Outline: User can continue shopping
        Given user is on Saucedemo dashboard page
        When user views inventories
        When user click button add to cart <add> time/times
        When user click shopping cart icon
        Then user redirect to cart detail page
        When user click continue shopping button
        Then user back to dashboard page
        And close browser

        Examples:
            | add | remove | result |
            | 2   | 1      | 1      |
