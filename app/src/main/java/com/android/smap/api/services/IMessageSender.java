package com.android.smap.api.services;

import com.android.smap.models.TextMessage;

public interface IMessageSender {
    public void sendMessage(TextMessage message);
}
