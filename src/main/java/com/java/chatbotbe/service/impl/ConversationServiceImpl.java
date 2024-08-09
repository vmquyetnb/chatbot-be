package com.java.chatbotbe.service.impl;

import com.java.chatbotbe.entity.Conversation;
import com.java.chatbotbe.model.ConversationModel;
import com.java.chatbotbe.repository.ConversationRepo;
import com.java.chatbotbe.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConversationServiceImpl implements ConversationService {

    @Autowired
    private ConversationRepo conversationRepo;

    @Override
    public List<ConversationModel> getConversationByUser(Long userId) {
        List<ConversationModel> result = new ArrayList<>();
        List<Conversation> conversations = conversationRepo.findConversationByUserId(userId);
        for(Conversation conversation : conversations){
            ConversationModel conversationModel = new ConversationModel();
            conversationModel.setTitle(conversation.getTitle());
            conversationModel.setCreatedDate(conversation.getCreatedDate());
            result.add(conversationModel);
        }
        return result;
    }
}
