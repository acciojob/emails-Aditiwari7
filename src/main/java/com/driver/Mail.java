package com.driver;

import java.util.Date;

public class Mail {
    Date date;
    String SenderId;
    String message;

    public Mail(Date d1, String sender, String mailmessage){
        this.date = d1;
        this.SenderId = sender;
        this.message = mailmessage;
    }
}
