Feature: Login to Application with credentials
  
   @LoginwithCredentials @smoke
   Scenario: Login with credentials
      
    Given I am on Login page 
    When I enters "Valid" credentials: username and a apassword 
    Then I should be able to login sucessfully
    And I logout  
    
   @LoginInvalidCredentials @NegativeTest @smoke
   Scenario: Login with Invalid credentials
      
    Given I am on Login page 
    When I enters "<invalid>" credentials: username and a apassword  
    Then I should get "<errormsg>" error message 
    
     Examples:
   | invalid | errormsg            |
   | Invalid | Invalid Credentials |
    
    
  
  
   
  