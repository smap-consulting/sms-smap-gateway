package com.android.smap.models;

/**
 * Created by matt on 25/09/14.
 */
public class SmapTextMessage extends TextMessage{

    private boolean isValid;
    private boolean isCommand;

    public SmapTextMessage(String number, String message){
        super(number, message);
    }
    public boolean isValid() {
        return isValid;
    }
    public boolean isCommandSMS() {return isCommand;}

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public void setCommand(boolean command) {
        this.isCommand = command;
    }
}
