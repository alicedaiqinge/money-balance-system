# README #

## Money Balance System ##

This service provides functions of balance system for multiple customers, etc.

## Description & Examples ##
● Deposit, withdraw and maintain a balance for multiple customers
● Return a customer’s balance and the bank’s total balance
● Prevent customers from withdrawing more money than they have in their account
When Alice deposits $30 and withdraws $20
Then Alice’s balance will be $10 and the bank’s balance will be $10
And Alice will be prevented from withdrawing $11 to prevent her balance going negative

## Directory Structure ##
```bash
   pom.xml             # Maven project build definition
   src/
     main/
       java/       # Java source code
       resources/  # Application resources
     test/
       java/       # Java test source code
       resources/  # Application test resources
```

## Configuration  ##
Java JDK 17, Springboot, Maven, Lombok

## Run Via Maven##
mvn clean install