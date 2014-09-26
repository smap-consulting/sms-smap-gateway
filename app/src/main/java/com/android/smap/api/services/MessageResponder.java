package com.android.smap.api.services;

import android.util.Log;

import com.android.smap.api.models.Contact;
import com.android.smap.di.DataManager;
import com.android.smap.models.SmapTextMessage;
import com.google.inject.Inject;

public class MessageResponder {

    private static final String TAG = MessageResponder.class.getCanonicalName();
    private final DataManager dataManager;

    @Inject
    public MessageResponder(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void handleMessage(IMessageSender sender, SmapTextMessage message) {

       if(message == null){
           Log.e(TAG, "Not Related Smap Message");
           return;
       }


//        Contact contact = dataManager.findContactByPhoneNumber(message.getPhoneNumber());
//        if (contact == null) {
//            Log.e(TAG, "Contact Not A Smap Contact");
//            return;
//        }


        SmapTextMessage reply = new SmapTextMessage(message.getPhoneNumber(),"hello world");
        sender.sendMessage(reply);

    }

}
