package comp3350.team7.scheduleapp.logic;



import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import comp3350.team7.scheduleapp.presentation.InvalidInputDialogFragment;

/*
 * Created By Thai Tran on 22 February,2021
 *
 */

public class EventValidator {

    /*
    * Return true if eName match combination of
    * word character,
    * number,
    * white spaces
    */
    public static boolean eventNameValidation(String eName){
        Pattern pattern = Pattern.compile("[\\w\\s*]+");
        Matcher matcher = pattern.matcher(eName);
        return matcher.matches();
    }

    /*
    * return true if the eStart is set after now
    */
    public static boolean eventStartTimeValidation(Calendar eStart, Calendar now) {
        return eStart.after(now);
    }

    /*
    * return true if eEnd set after eStart and after now
    */
    public static boolean eventEndTimeValidation(Calendar eEnd, Calendar eStart, Calendar now){
        return eEnd.after(eStart) && eEnd.after(now);
    }
}
