Feature: Login to Application with credentials
  
   @LoginwithCredentials @smoke
   Scenario: Login with credentials
      
    Given I am on Login page 
    When I enters "Valid" credentials: username and a apassword 
    Then I should be able to login sucessfully
    And I logout

  @LoginInvalidCredentials @NegativeTest @smoke
  Scenario Outline: Login with Invalid credentials
    Given I am on Login page
    When I enters <credential> credentials: username and a apassword
    Then I should get <error> error message
    Examples:
      | credential  | error        |
      | "<invalid>" | "<errormsg>" |
    
    
  
  
   
  