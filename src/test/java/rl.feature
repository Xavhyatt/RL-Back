Feature: To have a Team Profile Page
  As an user
  I want to navigate to a team page using navigation bar
  So that I can see that teams information

Scenario: Navigate to Team Profile
  Given that you are on the home screen
  When the a team is clicked on the navbar 
  Then I am on the correct teams profile page
  
  Scenario: Navigate to Team Profile
  Given that you are on the home screen
  When I search for a team using the search bar 
  Then I am on the correct teams profile page
  
