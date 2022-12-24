package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    List<String> InboxList;
    List<String> senderList;
    Map<String, Date> mapInbox;
    Map<String, Date> mapTrash;

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;

        senderList = new ArrayList<>();
        InboxList = new ArrayList<>();
        mapInbox = new TreeMap<>();
        mapTrash = new TreeMap<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

        if(InboxList.size() == inboxCapacity){
            String txt = InboxList.remove(0);
            Date dte = mapInbox.get(txt);

            mapInbox.remove(txt);
            mapTrash.put(txt, dte);
        }

        senderList.add(sender);
        InboxList.add(message);
        mapInbox.put(message, date);
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        int index = InboxList.indexOf(message);
        if(index >= 0){
            senderList.remove(index);
            InboxList.remove(message);
            mapInbox.remove(message);
        }
        else if(mapTrash.containsKey(message)){
            mapTrash.remove(message);
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        int Size = InboxList.size();
        if(Size < 1){
            return null;
        }else{
            return InboxList.get(Size-1);
        }
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        int Size = getInboxSize();
        if(Size < 1){
            return null;
        }else{
            return InboxList.get(0);
        }
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count = 0;
        for(Date D : mapInbox.values()){
            if(D.compareTo(start)>=0 && D.compareTo(end)<=0)
                count++;
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return mapInbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return mapTrash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        mapTrash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
