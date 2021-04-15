What technical debt has been cleaned up
========================================

Prudent and Inadvertent Technical Debt
- We were storing int day, int hour, int minute, etc in our database. This is due to us not 
being aware of HSQLDB's built-in function and as its type system. This makes the 
database become error prone that generate a lot of silent bugs. That why we fail to ship one of our 
feature (Select Schedule on date) in Iteration 2.
- We fixed this by relying in on the HSQLDB date time type and using its supported date time built-in 
 function to query date-time related data.  
 
Reckless and Inadvertent Technical Debt
- We did not have a centralized utility time helper, all the code support: get time and date, set time and date format, display time, set timezone... 
is repeatedly scattered over the whole app. This created inconsistenties in timezone setting and the display format of time/day. 
- We fixed this by modularizing the helper function into a singleton called TimeController where we can change timezone 
and date and time's format in one place and have it be effective everywhere.  

Reckless and Deliberate Technical debt (Code-Like-Hell)
- Before, after everytime we initialize new event object, we have to call EventValidator to validate the event and surrounding 
the the validate call with try and catch to handle Invalid Event Exception (all of this happend in presentation layer).This made us to write a lot of boilerplate code 
just to create an event also allow inconsistent error handling
- we fixed this by moving the event creation responsibility to logic layer (in EventController class) and create a method name buildEvent(). 
where we initialize an event and validate it at the same time(we noticed that this is violate OCP, we could improve it using dependency injection to 
inject kinds of validator(say different validator for different kind of events likes birthday, conference event,..etc) as for now, we dont think 
it was necessary but we noticed that we have this as a technical debt and we accept it) 


Show links to a commit where you paid off technical debt. Write 2-5 sentences
that explain what debt was paid, and what its classification is. 

[Technical Debt for UserPersistence Integration Test](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/issues/82)
- Debt classification: Prudent and Deliberate Technical Debt
- How we paid off this Technical Debt
    - [Added a UserDBManager](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/commit/5dadbebdf04acfb48ffcbefab491460ff4824f13)
        - this would be handling the interactions with the UserPersistenceInterface and any of its implementations
    - [Added Unit test for UserDBManager](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/commit/4a73e768104734dd1e008a49278bb208f32b9565)
    - [Added Integration Test for UserDBManager](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/commit/b7fa5c06ba0cd78866c6b5651fb849c5c74acab8)

What technical debt did you leave?
==================================
-   As stated above, there is technical debt in (buildEvent method) in which we break the OCP (open-close principle).
    We only validate one type of event but we know in the future, we would want to add more type of event and therefore 
    need different validator to validate each type of event. 
-   Improved Option:
    -   dependency injection: inject validator to buildEvent method 
-   Currently, our application works with a fixed time zone. This will not work correctly when users change their 
    location while the app is running. In deployment environment, the app should be dynamically changing the time 
    zone based on where the user is currently located.  
    -  The app is also currently tested using fixed time zone, so testing can fail.  
-   Improved Option: 
    -  Allow user to select their time-zone of choice before using the app, or
    -  Dynamically change the time-zone whenever the users change time zones    

What one item would you like to fix, and can't? Anything you write will not
be marked negatively. Classify this debt.
- Making a reusable Acceptance test for creating a new account, This would be a Prudent and inadvertent Technical debt since we do not know how to fix it and cant trace why its not working.

Discuss a Feature or User Story that was cut/re-prioritized
============================================
### De-prioritize User story (show details of single event view, swipe left to remove, sorting event by date) and prioritize User story (show list of all event). 
This is because we were integrating the database into our app and not all developed features were functioning as expected. So we had 
to de-prioritize all the future features and re-prioritize some of our earlier features. 

When did you change the priority of a Feature or User Story? Why was it
re-prioritized? Provide a link to the Feature or User Story. This can be from any
iteration.

#### Features that were cut/re-prioritized to "FUTURE"
- [Notification of Event Change](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/issues/33)
- [Share Event](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/issues/32)
- [Share Schedule](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/issues/31)
- [Create a Group](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/issues/18)
- [Edit group](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/issues/23)
- [Join a Group](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/issues/20)
- [Leave a group](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/issues/22)
- User Stories
    - [Workplace Schedule](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/issues/26)
    - [Private Event](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/issues/34)

#### Why were they re-prioritized?
They have been reprioritized to FUTURE iterations as we do not have enough time and that our initial time estimates were way off on most of our features which led to technical debt and pushing some features to future iterations as well as the features needed further refined and broken down into smaller more components that we can work.

#### When were they Re-prioritized
They have been reprioritized near the end of Iteration 3 since we now know that we would not have enough time to implement and develop working products for these features.

Acceptance test/end-to-end
==========================

Write a discussion about an end-to-end test that you wrote. What did you test,
how did you set up the test so it was not flaky? Provide a link to that test.

Since our project would be working on managing each client's schedules, we would need to enable the users to have their own personal accounts to store the event schedules and so that we can easily find their Events in our Event database by using their userID as the primary key. This means that one of our high priority features would be to let the Users log into their accounts, thats why we had to write an automated End-to-End test (System test) in order to show the users all the possible scenarios that our Login page could do depending on the input that the client has provided. 

For the Login End-to-End test, we would have to test the following
- If the Components are easily visible
- The input fields for the Username and password to see whether they would be updating whenever any actions were taken
- Whether both login button and create new account button are clickable
- a scenario where the user entered a username but no password
- a scenario where the user entered a password but no username
- a scenario where the user entered a username or password that doesnt match the one in our User Database
- a scenario where the user entered their correct username and password information
- a scenario where the user clicked on the new account button

In order to prevent the End-to-End test from providing flakey results, we implemented a UserDBManager class that would cohesively handle the interaction between the UserDatabase and the Activity Screen without having them be highly coupled to any implementation of the database. The UserDBManager would be instantiated in the LoginActivity and the CreateAccountActivity and any methods or calls to the database would have to be passed through the UserDBManager. We also prepopulated the UserDatabase with an account for each of the member of the team and have it copied onto the device's memory whenever the App is started so that the device would always have the upto-date copy of the databases, we would then use one of the prepopulated accounts to test each scenario stated above to see whether the feature works as intended and if it provides enough feedback to the user so that they can adjust any input errors that they might have had or explain the input format the Login screen requires (i.e., Entering required input fields).

Login Acceptance/End-to-End Test
- [LoginSystemTest](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/development/app/src/androidTest/java/comp3350/team7/scheduleapp/LoginSystemTest.java)
    - Contains most of the scenarios above
- [LoginAcceptanceTest](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/development/app/src/androidTest/java/comp3350/team7/scheduleapp/LoginAcceptanceTest.java)
    - Contains the Scenario test for successfully logging in to the user account

Acceptance test, untestable
===============

What challenges did you face when creating acceptance tests? What was difficult
or impossible to test?

We are currently having an issue writing an automated test for successfully creating an account using Espresso and ActivityScenarioRule that would produce consistent results and would not require a fresh install everytime we want to test it, this led me to splitting it off from the rest of the End-to-End test for the CreateAccountActivity. The current challenge is to have the setup and teardown run perfectly before the test is started, I have tried making a TestUtility class to do the setup and cleanup as well as hard code the setup into the @Test before the actual test is run and hard code the cleanup after the test has been ran in order to try and ensure the reusability of this part of the End-to-End/Acceptance test. But it was not cleaning up after itself no matter what i tried, I assumed that this is because of a weird bug/interaction between the ActivityScenarioRule and having access to the HSQLDB implementation for the UserDatabase. for now, I made a branch just for the this part of the test so the rest of the team can give it a shot and maybe they can solve the issue (maybe i missed something and cant find it due to stressing out too much about the failed test) and if it still turns out as a flop, i would just have to drop it from the AcceptanceTest (100% code coverage tragedy).


Velocity/teamwork
=================

Did your estimates get better or worse through the course? Show some
evidence of the estimates/actuals from tasks.

Our Estimates got better and more realistic through the course as we learned to be more grounded and aware of what we are actually capable of implementing and working as well as experiencing any problems that might surface when we start working as a member of a software development team. During the brainstorm portion of the project development and as we were coming up of ideas on what features we wanted our project to have, We should have done a deeper dive into what each features require and break them down into smaller components/subtasks/steps to slowly implement the features while making sure to communicate how we think each component should interact with each other

A good example of this would be the Login/Create Account feature of our project which we ended up as technical debt that we had to pay off all the way from iteration 1 to fully paying it off at the start of iteration 3. We originally estimated that it would take 2 days worth of work from building from scratch to making the UI and to being fully working versus the over 30 days worth of work it actually took, We didn't fully thought out that we needed to implement a working backend/database and how we are going to implement it, How it would look like and how it would interact with other components of our project. 

In order to improve our Estimates, we decided to break down the features/task/debt we plan on doing into develper tasks and then making a checklist of steps we needed to do to incrementally complete the task/feature, we then focus on working on one step on the list before going down to the next one all the way until we finish the checklist and closing the Dev Tasks.

Evidence
- how our original estimates were
    - [Create an account](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/issues/14)
    - [User Login](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/issues/16)
- How our Estimates look like now
    - [DOCUMENTATION dev task](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/issues/84)
    - [System Test dev task](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/issues/98)
