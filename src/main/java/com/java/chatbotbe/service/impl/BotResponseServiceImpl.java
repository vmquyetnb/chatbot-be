package com.java.chatbotbe.service.impl;

import com.java.chatbotbe.entity.BotResponse;
import com.java.chatbotbe.repository.BotResponseRepo;
import com.java.chatbotbe.service.BotResponseService;
import com.java.chatbotbe.util.RedisMessageService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.List;

@Service
public class BotResponseServiceImpl implements BotResponseService {

    @Autowired
    private BotResponseRepo botResponseRepo;

    @Autowired
    private RedisMessageService redisMessageService;

    @PostConstruct
    public void loadBotResponsesToRedis() {
        redisMessageService.clearBotResponsesFromRedis();
        List<BotResponse> botResponses = botResponseRepo.findAll();
        for (BotResponse botResponse : botResponses) {
            redisMessageService.saveBotResponseToRedis(botResponse);
        }
    }
}
