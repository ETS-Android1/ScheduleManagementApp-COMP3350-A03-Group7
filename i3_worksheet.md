What technical debt has been cleaned up
========================================

// Todo classification 
- we was storing int day, int hour, int minute ..etc in our database, This is due to we was not 
figure out the existence of hsqldb's bultin function as well as it's type system. This makes the 
database become error prone that generate a lot of silent bugs. That why we fail to ship one of our 
feature(Select Schedule on date)in iteration 2.
- we fixed this by relying in on the hsqldb date time type and using it's supported date time builtin 
 function to query date time relate data.  
 
// Todo classification
- we was not have a centralize utility time helper, all the code support get time and date , set time and date format, display time,set timezone... 
is repeatedly scattered over the whole app. This created inconsistent in timezone setting and the display format of time/day. 
- we fixed this by modularize these helper function into a singleton called TimeController where we can change timezone 
and date and time's format in one place and have it's affective everywhere.  

// Todo  classification 
- Before, after everytime we initialize new event object, we have to call EventValidator to validate the event and surrounding 
the the validate call with try and catch to handle Invalid Event Exception (all of this happend in presentation layer).This make us to write a lot of boilerplate code 
just to create an event also allow inconsistent error handling
- we fixed this by moving the event creation responsibility to logic layer (in EventController class) and create a method name buildEvent(). 
where we initialize a event and validate it at the same time(we noticed that this is violate OCP, we could improve it using dependency injection to 
inject kinds of validator(say different validator for different kind of events likes birthday, conference event,..etc) as for now, we dont think 
it necessary but we notice we have this as a technical debt and we accept it) 



Show links to a commit where you paid off technical debt. Write 2-5 sentences
that explain what debt was paid, and what its classification is. 

What technical debt did you leave?
==================================
-   As stated above, there is technical debt in (buildEvent method) in which we break the OCP(open-close principle).
    We only validate one type of event but we know in the future, we would want to add more type of event and therefore 
    need different validator to validate each type of event. 
-   Improved Option:
    -   dependency injection: inject validator to buildEvent method 
-   Currently, our application work with fix time zone. This will not work correctly when users change their 
    location while the app is running. In deployment environment the app should be dynamically changing the time 
    zone base on where the user currently at.  
    -  The app is also currently tested using fix time zone, so testing can fail.  
-   Improved Option: 
    -  Allow user to select their time-zone of choice before using the app 
    -  Or, Dynamically change the time-zone whenever the users change their location    

What one item would you like to fix, and can't? Anything you write will not
be marked negatively. Classify this debt.

Discuss a Feature or User Story that was cut/re-prioritized
============================================
### De-prioritize User story(show details of single event view, swipe left to remove,sorting event by date) and prioritize User story(show list of all event). 
this is because we were integrate database into our app and all developed feature was not functioning as expected. So we have 
to de-prioritize all the future feature and re-prioritized some of our earlier feature. 

When did you change the priority of a Feature or User Story? Why was it
re-prioritized? Provide a link to the Feature or User Story. This can be from any
iteration.

Acceptance test/end-to-end
==========================

Write a discussion about an end-to-end test that you wrote. What did you test,
how did you set up the test so it was not flaky? Provide a link to that test.

Acceptance test, untestable
===============

What challenges did you face when creating acceptance tests? What was difficult
or impossible to test?

Velocity/teamwork
=================

Did your estimates get better or worse through the course? Show some
evidence of the estimates/actuals from tasks.
