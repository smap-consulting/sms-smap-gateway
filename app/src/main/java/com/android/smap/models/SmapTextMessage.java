package com.android.smap.models;

/**
 * Created by matt on 25/09/14.
 */
public class SmapTextMessage extends TextMessage{

    private boolean isValid;

    public SmapTextMessage(String number, String message){
        super(number, message);
    }
    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

}
