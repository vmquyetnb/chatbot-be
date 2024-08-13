package com.java.chatbotbe.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.chatbotbe.entity.BotResponse;
import com.java.chatbotbe.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Service
public class RedisMessageService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String MESSAGE_QUEUE = "messageQueue";
    private static final String BOT_RESPONSE = "botResponse";

    public void saveMessageToRedis(Message message) {
        redisTemplate.opsForList().rightPush(MESSAGE_QUEUE, Objects.requireNonNull(convertMessageToJson(message)));
    }

    public String getMessageFromRedis() {
        return redisTemplate.opsForList().rightPop(MESSAGE_QUEUE);
    }

    private String convertMessageToJson(Message message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Message convertJsonToMessage(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, Message.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    //botResponse
    public void saveBotResponseToRedis(BotResponse botResponse) {
        redisTemplate.opsForList().rightPush(BOT_RESPONSE, Objects.requireNonNull(convertBotResponseToJson(botResponse)));
    }

    public String getRandomBotResponseFromRedis() {
        Long size = redisTemplate.opsForList().size(BOT_RESPONSE);
        if (size == null || size == 0) {
            return null;
        }
        int randomIndex = new Random().nextInt(size.intValue());
        return redisTemplate.opsForList().index(BOT_RESPONSE, randomIndex);
    }

    private String convertBotResponseToJson(BotResponse botResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(botResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public BotResponse convertJsonToBotResponse(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, BotResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void clearBotResponsesFromRedis() {
        redisTemplate.delete(BOT_RESPONSE);
    }
}
