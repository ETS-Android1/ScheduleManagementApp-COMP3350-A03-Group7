Iteration 2 Worksheet
======
Paying off technical debt
---
##### LoginActivity Technical Debt
+ For iteration 1, We went with a dummy account coded into the LoginActivity to test the flow of the app and choosing to implemet a database later on in iteration 2 as well as separating the layers into different files. We also added a UserValidator class to check whether the user inputs are valid and to fix some of the bugs pointed out in the Issues.
+ [Paying off Technical debt for LoginActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/commit/882bfca8f0e8194f11a8b7fe3cd2f557d72c6aba)
+ This was a Inadvertently Reckless turned deliberately Prudent technical debt because we initially made it to get the flow of how the app would run and which activity it would start on, we also didnt fully implement it as we should have done and not leave it half done

##### CreateAccountActivity Technical Debt
+ Similar to the technical Debt for LoginActivity, we did not implement a working backend nor properly separated the logic and Presentation layer into two separate files. Now we are paying it off by having it compose a UserValidator object that would check if the user inputs are valid and as well as make calls to the database to check if the userID is already taken. As for the backend issue, we paid it off by making a UserPersistenceInterface that was implemented by UserPersistenceStub for testing and by UserPersistanceHSQLDB for the actual database interactions between the Seams of the layers.
+ [Paying off technical debt for CreateAccountActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/commit/8af93f9817e5b628b2b0dad9027be4aa9c9754b4)
+ it was a Deliberately Prudent technical debt because we chose to implement it to get the flow of the app going and did not implement the required layer separation, the logic class to check if the userID was in the database and the actual database itself.


Design Patterns
------
+ [Decorator Pattern](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/development/app/src/main/java/comp3350/team7/scheduleapp/persistence/hsqldb/UserDBException.java)
+ [Singleton Pattern](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/development/app/src/main/java/comp3350/team7/scheduleapp/logic/UserValidator.java)
+ [Factory Pattern](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/development/app/src/main/java/comp3350/team7/scheduleapp/persistence/EventPersistenceInterface.java)
+ [Composition](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/development/app/src/main/java/comp3350/team7/scheduleapp/presentation/activity/LoginActivity.java)

Iteration 1 Feedback fixes
------
[Cannot Log in after creating an account](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/issues/64)

##### Issue:
 There was no database to store the Account that the grader made and thus cannot log in with a user created account.

##### Fixes: 
created UserPersistenceInterface and UserPersistenceHSQLDB that would be creating the User Database needed for LoginActivity and CreateAccountActivity, We've also added a UserValidator to manage the database interactions from both Activities. This will handle the newly created user account into the database by using a UserValidator object to check if the userID is a Unique ID (not in the database) before adding it into the database. As for the LoginActivity, it would also use a UserValidator object to check if the userID exists in the database and if the password entered by the user matches the password  for the userID that is in our database.

#### Links to fixes:
+ [User Database implementation](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/commit/405f465434b4cc2d83457dcfa63912a2de60ac92)
+ [CreateAccountActivity & LoginActivity Fix](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/commit/cc4188f0220863e51c60f571309dfbf275e2af28)
+ [Issue #63 fix](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/commit/4c30df5026a439202b6fe873233ed323d2027226)
+ [UserValidator](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/commit/e9ff140a534875cc67819fecf21d46c6291e8a82)
