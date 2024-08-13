package com.java.chatbotbe.service.impl;

import com.java.chatbotbe.Constants;
import com.java.chatbotbe.entity.BotResponse;
import com.java.chatbotbe.entity.Conversation;
import com.java.chatbotbe.entity.Message;
import com.java.chatbotbe.model.MessageModel;
import com.java.chatbotbe.repository.ConversationRepo;
import com.java.chatbotbe.repository.MessageRepo;
import com.java.chatbotbe.service.MessageService;
import com.java.chatbotbe.util.RedisMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private RedisMessageService redisMessageService;

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private ConversationRepo conversationRepo;

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Override
    public MessageModel sendMessage(Long conversationId, MessageModel messageModel) {

        Conversation conversation = conversationRepo.findConversationById(conversationId);

        Message message = new Message();
        message.setConversation(conversation);
        message.setContent(messageModel.getContent());
        message.setSentTime(LocalDateTime.now());
        message.setSender(Constants.ROLE_USER);

        redisMessageService.saveMessageToRedis(message);

        String messageJson = redisMessageService.getMessageFromRedis();
        if (messageJson != null) {
            Message messageRes = redisMessageService.convertJsonToMessage(messageJson);
            messageRepo.save(messageRes);

            if (message.getSender().equals(Constants.ROLE_USER)) {
                String botResponseJson = redisMessageService.getRandomBotResponseFromRedis();
                BotResponse botResponse = redisMessageService.convertJsonToBotResponse(botResponseJson);
                scheduler.schedule(() -> {

                    Message botMessage = new Message();
                    botMessage.setConversation(message.getConversation());
                    botMessage.setContent(botResponse.getResponse());
                    botMessage.setSentTime(LocalDateTime.now());
                    botMessage.setSender(Constants.ROLE_BOT);

                    redisMessageService.saveMessageToRedis(botMessage);

                    messageRepo.save(botMessage);

                    messagingTemplate.convertAndSend("/topic/messages", botMessage);
                }, 30, TimeUnit.SECONDS);
            } else {
                messagingTemplate.convertAndSend("/topic/messages", messageRes);
            }
        }

        messagingTemplate.convertAndSend("/topic/messages", message);

        messageModel.setContent(message.getContent());
        messageModel.setSentTime(message.getSentTime());
        messageModel.setSender(message.getSender());
        return messageModel;
    }


    @Override
    public List<MessageModel> getMessageByConversationId(Long conversationId) {
        List<MessageModel> result = new ArrayList<>();
        List<Message> messages = messageRepo.findMessagesByConversationId(conversationId);
        for(Message message : messages){
            MessageModel messageModel = new MessageModel();
            messageModel.setContent(message.getContent());
            messageModel.setSender(message.getSender());
            messageModel.setSentTime(message.getSentTime());
            result.add(messageModel);
        }
        return result;
    }
}
