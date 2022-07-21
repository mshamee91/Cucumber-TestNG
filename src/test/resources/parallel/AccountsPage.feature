Feature: Accounts page validation

Background:
Given User launches url
And Clicks on Sign in link

@smoke
Scenario Outline: Verify Accounts page title
Given User has logged into application with data available in "<SheetName>" and <RowNo>
Then Accounts page title should be displayed as "My account - My Store"
Examples:
|SheetName		|RowNo |
|MyAccounts		|1		 |
|MyAccounts		|2		 |