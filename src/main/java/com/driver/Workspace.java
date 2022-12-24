package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;


public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId, Integer.MAX_VALUE);
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar = new ArrayList<>();
        calendar.add(meeting);

        for(int i=0; i<calendar.size()-1; i++){
            for(int j=0; j<calendar.size()-1-i; j++){
             Meeting meet1 = calendar.get(i);
             Meeting meet2 = calendar.get(j);
             LocalTime start1 = meet1.getStartTime();
             LocalTime start2 = meet2.getStartTime();

             int val = start1.compareTo(start2);
             if(val > 0){
                 Meeting temp = meet1;
                 meet1 = meet2;
                 meet2 = temp;
             }
            }
        }
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        int count = 0;
        LocalTime start = LocalTime.parse("00:00");
        LocalTime end = LocalTime.parse("00:00");

        for(Meeting curr : calendar){
            LocalTime currStart = curr.getStartTime();
            LocalTime currEnd = curr.getEndTime();

            if(currStart.compareTo(end) > 0){
                start = currStart;
                end = currEnd;
                count++;
            }
        }
        return count;
    }
}