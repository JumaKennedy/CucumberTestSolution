Feature: Update profile information 
  
   @UpdateUserInfo
   Scenario: profile update
      
    Given I login with my credentials  
     When I enters "Valid" credentials: username and a apassword         
    Then I fill in the profile update form and submit
    Then My information should be changed sucessfully
    And I logout
  
  
   
  