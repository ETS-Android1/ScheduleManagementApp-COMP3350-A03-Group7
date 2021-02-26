# Iteration 1 


## Presentation Layer

### Activity
[activity/ScrollingActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a03/Team-7/-/blob/master/app/src/main/java/comp3350/team7/scheduleapp/presentation/activity/ScrollingActivity.java)
- View containing list of event and a button, when on click will make a transition to create event activity 
[activity/EventCreationActivity]()
- View for adding event details before create it with a save button
[activity/LoginActivity]()
- Login or Create Account 
- If login:
    - View for adding authentication details of user, before transition to ScrollingActivity
- else: 
    - transition to CreateAccoutActivity
[activity/CreateAccountActivity]()
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


## Logic Layer 

- [EventController]()
    - This is where we put the logic, the controller validate user input and ask data from persisten
    - when the data is available, control pass it to view 
    - Handle exception from database
    - Throw exception to the presentation layer, if they are the caller and 
- [EventValidator]()
- [SORTNAME]()

### Comparators

[EventEndAscendingComparator]()
[EventEndDescendingComparator]()
[EventStartAscendingComparator]()
[EventStartDescendingComparator]()

### exceptions 

[DbErrorException]()
[InvalidEventException]()
[RandomException]()

### logTag

[TAG]()




## Persistent Layer

[EventDB]()
[EventDbStub]()
