package com.java.chatbotbe.service.impl;

import com.java.chatbotbe.entity.Conversation;
import com.java.chatbotbe.entity.Message;
import com.java.chatbotbe.model.MessageModel;
import com.java.chatbotbe.repository.ConversationRepo;
import com.java.chatbotbe.repository.MessageRepo;
import com.java.chatbotbe.repository.UserRepo;
import com.java.chatbotbe.service.BotService;
import com.java.chatbotbe.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ConversationRepo conversationRepo;

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private BotService botService;

    @Override
    public MessageModel sendMessage(Long conversationId, MessageModel messageModel) {
        Conversation conversation = conversationRepo.findConversationById(conversationId);

        Message message = new Message();
        message.setConversation(conversation);
        message.setContent(messageModel.getContent());
        message.setSentTime(LocalDateTime.now());
        messageRepo.save(message);

        String botResponse = botService.replyWithDelay(conversation);

        messageModel.setContent(messageModel.getContent());
        messageModel.setBotResponse(botResponse);
        return messageModel;
    }
}
