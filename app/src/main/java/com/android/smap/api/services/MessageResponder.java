package com.android.smap.api.services;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.android.smap.GatewayApp;
import com.android.smap.R;
import com.android.smap.api.models.Contact;
import com.android.smap.api.models.Dialogue;
import com.android.smap.di.DataManager;
import com.android.smap.models.SmapTextMessage;
import com.android.smap.samuel.Samuel;
import com.google.inject.Inject;

import org.smap.surveyConverser.SurveyConverser;

public class MessageResponder {

    private static final String TAG = MessageResponder.class.getCanonicalName();
    private final DataManager dataManager;
    private Resources resource;

    private String[] testConversation =  new String[]{"Q1 : What bird did you see?",
            "Q2 : Was it just the ONE bird?",
            "Q3 : Want anything from the shops?"};

    @Inject
    public MessageResponder(DataManager dataManager) {
        this.dataManager = dataManager;

    }

    public void setResource(Resources res){
        this.resource = res;
    }
    public void handleMessage(IMessageSender sender, SmapTextMessage message) {

       if(message == null){
           Log.e(TAG, "Not Related Smap Message");
           return;
       }

        Contact contact = dataManager.findContactByPhoneNumber(message.getPhoneNumber());
        if (contact == null) {
            Log.e(TAG, "Contact Not A Smap Contact : "+message.getPhoneNumber());
            return;
        }
        if(!contact.isActive()){
            notifySenderNotActive(sender, message);
            return;
        }
        if(message.isCommandSMS()){
            String log = "SMAP COMMAND REQUESTED FROM "+message.getPhoneNumber();
            Log.i(Samuel.class.getName(), log);
            //handle commands here
            return;
        }

        /*
        //get Dialogue is actually active for
        Dialogue dialogue = contact.getActiveDialogue();

        //parse answer
        String answer = message.getMessageBody();

        //Log answer
        dialogue.logReceivedMessage(answer);

        //Inflate JR
        String savedSurveyData = dialogue.getSurveyData();
        SurveyConverser converser = SurveyConverser.resume(savedSurveyData);

        //Answer question
        converser.answerCurrentQuestion(answer);

        if(converser.isComplete()) {
            //Contact has completed survey
            contact.setActive(false);
            return;
        }

        //Get question to send back
        String nextQuestionText = converser.getCurrentQuestion();

        //Send next message
        SmapTextMessage reply = new SmapTextMessage(message.getPhoneNumber(), nextQuestionText);
        sender.sendMessage(reply);

        //Log response
        dialogue.logSentMessage(nextQuestionText);
        */

        int index = GatewayApp.getAppConfig().getCount();
        if(index < 3){
           String replyMessage =  testConversation[index];
           GatewayApp.getAppConfig().incrementCounter();
           SmapTextMessage reply = new SmapTextMessage(message.getPhoneNumber(), replyMessage);
           sender.sendMessage(reply);
        }
        else{

           GatewayApp.getAppConfig().incrementCounter();
           contact.setActive(false);
           notifySenderNotActive(sender, message);
       }
    }

    public void notifySenderNotActive(IMessageSender sender, SmapTextMessage message){
        SmapTextMessage reply = new SmapTextMessage(message.getPhoneNumber(),
                resource.getString(R.string.response_contact_not_active));
        sender.sendMessage(reply);
    }
}
