package com.java.chatbotbe.service;

import com.java.chatbotbe.entity.Conversation;
import com.java.chatbotbe.entity.Message;
import com.java.chatbotbe.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BotService {

    @Autowired
    private MessageRepo messageRepo;

    @Async
    public String replyWithDelay(Conversation conversation) {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Message botMessage = new Message();
        botMessage.setConversation(conversation);
        botMessage.setContent("This is an automated reply from the bot.");
        botMessage.setSentTime(LocalDateTime.now());

        messageRepo.save(botMessage);
        return botMessage.getContent();
    }


}
