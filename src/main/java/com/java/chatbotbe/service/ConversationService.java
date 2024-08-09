package com.java.chatbotbe.service;

import com.java.chatbotbe.model.ConversationModel;

import java.util.List;

public interface ConversationService {
    List<ConversationModel> getConversationByUser(Long userId);
}
