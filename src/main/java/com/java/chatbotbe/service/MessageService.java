package com.java.chatbotbe.service;

import com.java.chatbotbe.entity.Message;
import com.java.chatbotbe.model.MessageModel;

public interface MessageService {
    MessageModel sendMessage (Long conversationId, MessageModel messageModel);
}
