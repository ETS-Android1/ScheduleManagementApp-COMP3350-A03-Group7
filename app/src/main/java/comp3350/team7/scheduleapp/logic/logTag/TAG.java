package comp3350.team7.scheduleapp.logic.logTag;

/*
 * Created By Thai Tran on 24 February,2021
 *
 */


public enum TAG {
    ScrollingActivity("ScrollingActivity"),
    RecyclerViewOnItemtouchHelper("RecylerViewItemTouchHelper"),
    RecyclerViewAdapter("RecyclerViewAdapter"),
    CreateEventActivity("CreateEventActivity");
    String text;

    TAG(String text) {
        this.text = text;
    }

    public String toString() {
        return text;
    }
}
