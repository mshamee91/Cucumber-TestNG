Feature: Check Login page with different inputs

Background:
Given User launches url
And Clicks on Sign in link
Then Login page is displayed

@smoke
Scenario Outline: Login with correct username and correct password
When user enters correct "<username>" and correct "<Password>"
And clicks on Signin button
Then MyAccount page is displayed as "My account - My Store"
Examples:
|username											|Password			|
|cucumberpractice00@gmail.com	|practice00		|