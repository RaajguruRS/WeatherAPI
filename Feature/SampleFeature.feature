Feature: A traveller is to pack an upcoming business trip
@test
Scenario Outline: verify the weather is deemed as cold when mercury dip is below 20 degress celcius

When user performs "GET" action on the "<API>" weather forecast API successfully
Then user validates "Mercury Dip" is "Below Twenty" degree celcius leading to "Cold" weather condition

Examples:
|API|
|MercuryDip|
Scenario Outline: verify the weather is not deemed as cold when mercury dip is above 20 degress celcius

When user performs "GET" action on the "<API>" weather forecast API successfully
Then user validates "Mercury Dip" is "Above Twenty" degree celcius leading to "Hot" weather condition

Examples:
|API|
|MercuryDip|

Scenario Outline: verify if the traveller packs woollen suit when temp is cold

When user performs "GET" action on the "<API>" weather forecast API successfully
Then user validates if temparature is "Cold" so that traveller packs "woollen" suits

Examples:
|API|
||

Scenario Outline: verify if the traveller packs swim suit when temp is hot

When user performs "GET" action on the "<API>" weather forecast API successfully
Then user validates if temparature is "Hot" so that traveller packs "Swimming" suits
Examples:
|API|
||


Scenario Outline: verify if the traveller is advised with push notification on weather before embarking on the trip

When user performs "GET" action on the "<API>" weather forecast API successfully
Then user validates if the traveller is advised with "push notification" on weather before embarking on the trip

Examples:
|API|
|pushNotification|