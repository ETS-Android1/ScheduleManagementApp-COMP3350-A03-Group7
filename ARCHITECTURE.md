# Iteration 1 

## Architecture diagram 

![architecture](Architecture_Iter2.jpg)

## Presentation Layer

### Activity
[activity/ScrollingActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/presentation/activity/ScrollingActivity.java)
- View containing list of event and a button, when on click will make a transition to create event activity 
[activity/EventCreationActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/presentation/activity/EventCreationActivity.java)
- View for adding event details before create it with a save button
[activity/LoginActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/presentation/activity/LoginActivity.java)
- Login or Create Account 
- If login:
    - View for adding authentication details of user, before transition to ScrollingActivity
- else: 
    - transition to CreateAccoutActivity
[activity/CreateAccountActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/presentation/activity/CreateAccountActivity.java)
- View for user to adding user details and set password before transition to ScrollingActivity


### Adapter 
[adapter/RecyclerViewAdapter](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/presentation/adapter/RecyclerViewAdapter.java)
- A adapter for recyclerView where each view holder store Event details 
- Adapter put EventController in work and notify view changes when detect user action like remove, undo, add,..
 
### Fragment
[fragment/InvalidInputDialogFragment](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/presentation/fragment/InvalidInputDialogFragment.java)
- A dialog fragment that show warning message for invalid input 

### UI Helper
[UiHelper/ItemOffsetDecoration](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/presentation/UiHelper/ItemOffsetDecoration.java)
- Decoration, create offset between each viewholder in recycler view
[UiHelper/RecyclerViewOnItemtouchHelper](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/presentation/UiHelper/RecyclerViewOnItemtouchHelper.java)
- Gesture detection, currently support swipe right


## Logic Layer 

- [logic/EventController](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/logic/EventController.java)
    - This is where we put the logic, the controller validate user input and ask data from persisten
    - when the data is available, control pass it to view 
    - Handle exception from database
    - Throw exception to the presentation layer, if they are the caller and have given an invalid argument  
- [logic/EventValidator](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/logic/EventValidator.java)
    - validation for an event 
- [logic/SORTNAME](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/logic/SORTNAME.java)
    - enum class, contains many way of sorting we want to use 
- [logic/UserValidator](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/logic/UserValidator.java)
    - Used to validate any user input from LoginActivity and CreateAccountActivity
    - accepts any implementation of the UserPersistenceInterface
    - Checks if the User is a valid User in the Database
-[logic/ScheduleController]()
    - Accepts any implementation of SchedulePersistenceInterface
    - Gets the Schedule for the user on the selected date
   
### Comparators
[EventEndAscendingComparator](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/logic/comparators/EventEndAscendingComparator.java)
- sort base on event end time in ascending order

[EventEndDescendingComparator](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/logic/comparators/EventEndDescendingComparator.java)
- sort base on event end time in descending order

[EventStartAscendingComparator](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/logic/comparators/EventStartAscendingComparator.java)
- sort base on event start time in ascending order

[EventStartDescendingComparator](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/logic/comparators/EventStartDescendingComparator.java)
- sort base on event start time in ascending order


### exceptions 
[DbErrorException](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/logic/exceptions/DbErrorException.java)
- Custom Database exception
- Sends a message to the User to inform them of what went wrong, why it went wrong and how to correct it. 

[InvalidEventException](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/logic/exceptions/InvalidEventException.java)
- Custom invalid event exception

[RandomException](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/logic/exceptions/RandomException.java)
- this is special, for creating fake database, use only to create random event in EventDbStub

[UserDBException]() add link after push
- Custom Exception for when User input could not be matched to anything in the UserDatabase

### logTag

[TAG](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/logic/logTag/TAG.java)
- Use to store all the Log tag
- we use Log to store debug as well as error info, the tag contains the name of the class when it being call from
- so when we see the log file, we know where log message come from  

## Application
[Main](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/Application/Main.java)
- holds the db file path name
- Can instantiate new databases files at a given path
- returns the path to the db file

[DbServiceProvider](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/Application/DbServiceProvider.java)
- Singleton instantiation of the Databases
    - User Database
    - Schedule Database
    - Event Database 
- Gives access to the databases based on what database is requested
- Reloads the Database files in cases where the databases are unsynced


## Persistent Layer

[EventPersistenceInterface](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/persistence/EventDB.java) Change the link after push
- interface for all event database implementation


[EventPersistenceStub](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/persistence/EventDbStub.java) Change the link after pushing to master
- Stub with concrete information
- Used for integration testing of the Event Database

[EventPersistenceHSQLDB](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/persistence/hsqldb/EventPersistenceHSQLDB.java)
- Actual HSQLDB Implementation for EventPersistence
- Persistent database that saves into a database file in Assets/db

[UserPersistenceInterface](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/persistence/UserPersistenceInterface.java)
- Design by contract for all UserPersistence implementation

[UserPersistenceStub](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/persistence/stubs/UserPersistenceStub.java)
- Stub that contains 4 entries (the memebers of Group 7)
- Used for Integration testing

[UserPersistenceHSQLDB](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/persistence/hsqldb/UserPersistenceHSQLDB.java)
- Actual HSQLDB Implementation for UserPersistence
- Persistent database that saves into a database file in Assets/db

[SchedulePersistenceInterface]()
- Design by contract for all implementations of SchedulePersistence

[SchedulePersistenceStub]()
- Fake Implementation of a Schedule Database/Persistence
- Used for Integration testing of SchedulePersistence

[SchedulePersistenceHSQLDB]()
- Actual HSQLDB Implementation for SchedulePersistence
- Persistent database that saves into a database file in Assets/db
