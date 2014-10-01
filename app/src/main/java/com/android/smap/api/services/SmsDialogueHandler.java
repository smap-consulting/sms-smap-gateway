package com.android.smap.api.services;


import com.android.smap.api.models.Dialogue;
import com.android.smap.models.SmapTextMessage;
import com.android.smap.models.TextMessage;

import org.smap.DialogueHandler;
import org.smap.SurveyConversation;

public class SmsDialogueHandler implements DialogueHandler {
    private final Dialogue dialogue;
    private final MessageSender sender;
    private final SmapTextMessage message;

    public SmsDialogueHandler(Dialogue dialogue, MessageSender sender, SmapTextMessage message) {
        this.dialogue = dialogue;
        this.sender = sender;
        this.message = message;
    }

    public SmsDialogueHandler(Dialogue dialogue, MessageSender sender) {
        this.dialogue = dialogue;
        this.sender = sender;
        this.message = null;
    }

    @Override
    public String loadData() {
        return dialogue.getSerializedState();
    }

    @Override
    public void saveData(String data, String answers) {
        dialogue.saveData(data, answers);
    }

    @Override
    public String getAnswerText() {
        return message.getMessageBody();
    }

    @Override
    public void reply(String response) {
        TextMessage replyMessage = new TextMessage(dialogue.getPhoneNumber(), response);
        dialogue.logMessage(replyMessage);
        sender.sendMessage(replyMessage);
    }

    @Override
    public void handleComplete() {
        dialogue.markAsComplete();
    }

    @Override
    public String getFormXml() {
        return dialogue.getFormXML();
    }

    @Override
    public void recordSurveyDetails(SurveyConversation surveyConversation) {
        dialogue.setInstanceXml(surveyConversation.getAnswers());
        dialogue.save();
    }
}
