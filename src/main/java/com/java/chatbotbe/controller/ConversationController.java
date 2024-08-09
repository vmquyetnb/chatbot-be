package com.java.chatbotbe.controller;

import com.java.chatbotbe.Constants;
import com.java.chatbotbe.model.ChatbotDefautRespone;
import com.java.chatbotbe.model.ConversationModel;
import com.java.chatbotbe.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/conversation")
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @GetMapping("")
    public ChatbotDefautRespone<?> getConversationByUser (@RequestParam Long userId){
        ChatbotDefautRespone<List<ConversationModel>> respone = new ChatbotDefautRespone<>();
        try{
            List<ConversationModel> conversationModelList = conversationService.getConversationByUser(userId);
            respone.setError(Constants.SUCCESS_CODE);
            respone.setMessage(Constants.SUCCESS_MESSAGE);
            respone.setDetailMessage(Constants.SUCCESS_MESSAGE);
            respone.setOutput(conversationModelList);
        }catch (Exception e){
            respone.setError(Constants.SERVER_ERROR_CODE);
            respone.setMessage(Constants.SERVER_ERROR_MESSAGE);
            respone.setDetailMessage(e.getMessage());
        }
        return respone;
    }
}
