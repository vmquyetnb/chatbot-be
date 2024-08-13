package com.java.chatbotbe.service;

import com.java.chatbotbe.entity.Message;
import com.java.chatbotbe.model.MessageModel;

import java.util.List;

public interface MessageService {
    MessageModel sendMessage (Long conversationId, MessageModel messageModel);
    List<MessageModel> getMessageByConversationId (Long conversationId);
}
