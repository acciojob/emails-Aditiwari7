package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    List<Mail> Inbox;
    List<Mail> Trash;

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;

        Inbox = new ArrayList<>();
        Trash = new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

        if(Inbox.size() == inboxCapacity){
            Trash.add(Inbox.remove(0));
        }
        Inbox.add(new Mail(date, sender, message));
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        int flag = 0;
        for(int i=0; i< Inbox.size(); i++){
            Mail currmail = Inbox.get(i);
            if(currmail.message.equals(message)){
                Inbox.remove(i);
                flag = 1;
            }
        }

        if(flag == 0){
            for(int i=0; i<Trash.size(); i++){
                Mail currmail = Trash.get(i);
                if(currmail.message.equals(message)){
                    Trash.remove(i);
                }
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        int Size = Inbox.size();
        if(Size == 0){
            return null;
        }
        else{
            Mail m = Inbox.get(Size-1);
            return m.message;
        }
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        int Size = Inbox.size();
        if(Size == 0){
            return null;
        }
        else{
            Mail m = Inbox.get(0);
            return m.message;
        }
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count = 0;
        for(int i=0; i< Inbox.size(); i++){
            Mail m = Inbox.get(i);
            if(m.date.compareTo(start) > 0 && m.date.compareTo(end) < 0){
                count++;
            }
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return Inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return Trash.size();

    public void emptyTrash(){
        // clear all mails in the trash
        Trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
