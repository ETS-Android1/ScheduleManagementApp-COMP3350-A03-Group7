# Iteration 1 


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
[adapter/RecyclerViewAdapter]()
- A adapter for recyclerView where each view holder store Event details 
- Adapter put EventController in work and notify view changes when detect user action like remove, undo, add,..
 
### Fragment
[fragment/InvalidInputDialogFragment]()
- A dialog fragment that show warning message for invalid input 

### UI Helper
[UiHelper/ItemOffsetDecoration]()
- Decoration, create offset between each viewholder in recycler view
[UiHelper/RecyclerViewOnItemtouchHelper]()
- Gesture detection, currently support swipe right
[adapter/RecyclerViewAdapter](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/presentation/adapter/RecyclerViewAdapter.java)

### Fragment
[fragment/InvalidInputDialogFragment](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/presentation/fragment/InvalidInputDialogFragment.java)

### UI Helper
[UiHelper/ItemOffsetDecoration](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/presentation/UiHelper/ItemOffsetDecoration.java)
[UiHelper/RecyclerViewOnItemtouchHelper](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/presentation/UiHelper/RecyclerViewOnItemtouchHelper.java)


## Logic Layer 

- [logic/EventController]()
    - This is where we put the logic, the controller validate user input and ask data from persisten
    - when the data is available, control pass it to view 
    - Handle exception from database
    - Throw exception to the presentation layer, if they are the caller and have given an invalid argument 
- [logic/EventValidator]()
    - validation for an event 

- [logic/SORTNAME]()
    - enum class, contains many way of sorting we want to use 
   
### Comparators

[comparators/EventEndAscendingComparator]()
- sort base on event end time in ascending order
[comparators/EventEndDescendingComparator]()
- sort base on event end time in descending order
[comparators/EventStartAscendingComparator]()
- sort base on event start time in ascending order
[Ecomparators/ventStartDescendingComparator]()
- sort base on event start time in ascending order

### exceptions 

[exceptions/DbErrorException]()
- Custom Database exception
[exceptions/InvalidEventException]()
- Custom invalid event exception
[exceptions/RandomException]()
- this is special, for creating fake database, use only to create random event in EventDbStub

### logTag

[logTag/TAG]()
- Use to store all the Log tag
- we use Log to store debug as well as error info, the tag contains the name of the class when it being call from
- so when we see the log file, we know where log message come from  

## Persistent Layer

[persistent/EventDB]()
- interface for all event database 
[persistent/EventDbStub]()
- fake database, create random n number of fake event 