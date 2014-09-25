package com.android.smap.api.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

import java.util.Date;

/**
 * Audit table to hold a log of messages in a given survey dialogue
 */
public class LogMessage extends Model {

    @Column(name = "dialogue_id", onDelete = Column.ForeignKeyAction.CASCADE)
    private SurveyContact dialogue;

    @Column
    private String body;

    @Column
    private String phoneNumber;

    @Column
    private Date time;

    /**
     * null argument constructor for pulling records from the database
     */
    public LogMessage() {

    }

    public LogMessage(SurveyContact dialogue, String body, String phoneNumber) {
        this.dialogue = dialogue;
        this.body = body;
        this.phoneNumber = phoneNumber;
        this.time = new Date();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public SurveyContact getDialogue() {
        return dialogue;
    }

    public void setDialogue(SurveyContact dialogue) {
        this.dialogue = dialogue;
    }
}
