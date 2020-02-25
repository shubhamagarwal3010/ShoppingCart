## Shopping Cart 
version number `ec91d082895352739038310fe3f2e8351d5590ea`

---

#### Overview

All totals are formatted to 2 decimal places, rounded up i.e. 0.567 should result in 0.57 but 0.564 should result in 0.56; this is known as Round Half-Up or Arithmetic Rounding.

##### Step 1: Add products to the shopping cart.

Given:

    An empty shopping cart
    And a product, Dove Soap with a unit price of 39.99

When:

    The user adds 5 Dove Soaps to the shopping cart

Then:

    The shopping cart should contain 5 Dove Soaps each with a unit price of 39.99
    And the shopping cart’s total price should equal 199.95

---
##### Step 2: Add additional products of the same type to the shopping cart.

Given:

    An empty shopping cart
    And a product, Dove Soap with a unit price of 39.99

When:

    The user adds 5 Dove Soaps to the shopping cart
    And then adds another 3 Dove Soaps to the shopping cart

Then:

    The shopping cart should contain 8 Dove Soaps each with a unit price of 39.99
    And the shopping cart’s total price should equal 319.92

---
##### Step 3: Calculate the tax rate of the shopping cart with multiple items

Given:

    An empty shopping cart
    And a product, Dove Soap with a unit price of 39.99
    And another product, Axe Deo with a unit price of 99.99
    And a sales tax rate of 12.5%

When:

    The user adds 2 Dove Soaps to the shopping cart
    And then adds 2 Axe Deos to the shopping cart

Then:

    The shopping cart should contain 2 Dove Soaps each with a unit price of 39.99
    And the shopping cart should contain 2 Axe Deos each with a unit price of 99.99
    And the total sales tax amount for the shopping cart should equal 35.00
    And the shopping cart’s total price should equal 314.96