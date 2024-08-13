package com.java.chatbotbe.service;

import com.java.chatbotbe.entity.Conversation;
import com.java.chatbotbe.model.ConversationModel;

import java.util.List;

public interface ConversationService {
    List<ConversationModel> getConversation();
    ConversationModel save(ConversationModel conversationModel);
}
