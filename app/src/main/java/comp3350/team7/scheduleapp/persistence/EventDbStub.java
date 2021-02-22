package comp3350.team7.scheduleapp.persistence;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import comp3350.team7.scheduleapp.logic.exceptions.RandomException;
import comp3350.team7.scheduleapp.presentation.Event;

/*
 * Created By Thai Tran on 22/02/21 10:57 AM
 *
 */
public class EventDbStub implements EventDB {
    private final List<Event> eventList;
    private final int numEventStub;

    public EventDbStub(int numEventStub) {
        this.eventList = new ArrayList<Event>();
        this.numEventStub = numEventStub;

        CreateEventListStub();
    }


    private void CreateEventListStub() {
        Calendar randCalendar;
        String randEventName;
        String randEventDesciption = getRandEventDiscription();
        for (int i = 0; i < numEventStub; i++) {
            randCalendar = getRandCalendarInstance();
            randEventName = getRandEventName();
            eventList.add(new Event(randEventName, randEventDesciption,randCalendar));
        }
    }

    private Calendar getRandCalendarInstance() {
        Calendar calendar = Calendar.getInstance();
        int month, day, hour, minute;
        try {
            month = randBetween(0, 11);
            day = randBetween(0, 30);
            hour = randBetween(0, 23);
            minute = randBetween(0, 59);

            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            calendar.set(Calendar.HOUR, hour);
            calendar.set(Calendar.MINUTE, minute);
        } catch (RandomException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return calendar;


    }

    private String getRandEventDiscription() {
        // For now, return fix event discription
        String des = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
                     "tempor incididunt ut labore et dolore magna aliqua.";
        return des;

    }

    private String getRandEventName() {
        String To = "call,read,dress,buy,ring,hop,skip,jump,look,rob,find,fly,go,ask,raise,search";
        String And = "the,a,an,my,as,by,to,in,on,with";
        String Do = "dog,doctor,store,dance,jig,friend,enemy,business,bull,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday,Mom,Dad";
        String capitalizeFirstLetter = Character.toUpperCase(randomWord(To).charAt(0)) + To.substring(1);
        return capitalizeFirstLetter + randomWord(And) + randomWord(Do);


    }

    ;

    private String randomWord(String s) {
        String[] list;
        list = s.split(",");
        int ranInt = 0;
        try {
            ranInt = randBetween(0, list.length);
        } catch (RandomException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return list[ranInt];
    }

    private int randBetween(int start, int end) throws RandomException {
        int result = start + (int) Math.floor(Math.random() * (end - start));
        if (result > start && result <= end)
            return result;
        else {
            throw new RandomException("Error in generating random number");
        }

    }

    @Override
    public List<Event> getEventList() {
        return eventList;
    }

    @Override
    public List<Event> addEvent(Event e) {
        eventList.add(e);
        return eventList;
    }

    @Override
    public List<Event> removeEvent(Event e) throws DbErrorException {
        int index = eventList.indexOf(e);
        if (index < 0) {
            throw new DbErrorException("Event not exist in the database.");
        }
        eventList.remove(index);
        return eventList;
    }

    @Override
    public List<Event> updateEvent(Event e) throws DbErrorException {
        int index = eventList.indexOf(e);
        if (index < 0) {
            throw new DbErrorException("Event not exist in the database.");
        }
        eventList.set(index, e);
        return eventList;
    }
}

