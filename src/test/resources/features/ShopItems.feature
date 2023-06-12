Feature: Shop items and check out 
  
   @AddItemToCart
   Scenario: view and add item to cart then clear cart
      
    Given I am at home page 
    When I click on shop button from top menu
    Then I am taken to shop menu list and I select item
    And I add an Item to cart by selecting color, size and Qty
    Then I am taken to cart with Option to check out or continue shopping.
    But I change my mind, so I cancel the order by clearing the cart 
    Then The cart should be empty 
    
  