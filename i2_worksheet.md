Iteration 2 Worksheet
======

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
