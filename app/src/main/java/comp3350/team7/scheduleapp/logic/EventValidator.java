package comp3350.team7.scheduleapp.logic;


import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import comp3350.team7.scheduleapp.logic.exceptions.InvalidEventException;
import comp3350.team7.scheduleapp.objects.Event;

/*
 * Created By Thai Tran on 22 February,2021
 *
 */

public class EventValidator {
    final static int maxTitleLength = 60;
    final static int maxDescriptionLength =120;
    public static void valid(Event e) throws InvalidEventException {
        if (e != null && e.getEventStart() != null && e.getDescription() != null && e.getTitle() != null &&
                e.getUserName() != null) {
            validateEventName(e.getTitle());
            if (e.getEventEnd() != null)
                validateEventStartAndEndTime(e.getEventStart(), e.getEventEnd(), Calendar.getInstance());
            else {
                validateEventStartTime(e.getEventStart(), Calendar.getInstance());
            }
        } else {
            throw new InvalidEventException("Some thing missing");
        }
    }

    public static void validateEventDescription(String description) throws InvalidEventException{
        Pattern pattern = Pattern.compile("[\\w\\s*]+");
        Matcher matcher = pattern.matcher(description);
        if (!matcher.matches())
            throw new InvalidEventException("Invalid Event Description" +
                    "\nOnly accept any combination of Word character,number and white space");
        else if (description.length() > 120)
            throw new InvalidEventException("Invalid Event Description" +
                    "\nShould have at least 5 characters and no more than " + maxDescriptionLength + "characters");
    }
    /*
     * valid if eName match combination of
     * word character,
     * number,
     * white spaces
     */
    public static void validateEventName(String eName) throws InvalidEventException {
        Pattern pattern = Pattern.compile("[\\w\\s*]+");
        Matcher matcher = pattern.matcher(eName);
        if (!matcher.matches())
            throw new InvalidEventException("Invalid Event Title" +
                    "\nOnly accept any combination of Word character,number and white space");
        else if (eName.length() > 30)
            throw new InvalidEventException("Invalid Event Name" +
                    "\nShould have at least 5 characters and no more than " + maxTitleLength+ "characters");

    }

    /*
     * valid if the eStart is set after now
     */
    public static void validateEventStartTime(Calendar eStart, Calendar now) throws InvalidEventException {
        if (!eStart.after(now))
            throw new InvalidEventException("Invalid Event Start Time");


    }

    /*
     * valid if eEnd set after eStart and after now
     */
    public static void validateEventStartAndEndTime(Calendar eEnd, Calendar eStart, Calendar now) throws InvalidEventException {
        if (!eEnd.after(eStart))
            throw new InvalidEventException("Invalid Event End Time");
        else if(!eStart.after(now))
            throw new InvalidEventException("Invalid Event Start Time");
    }
}
