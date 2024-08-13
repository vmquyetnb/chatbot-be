package com.java.chatbotbe.controller;

import com.java.chatbotbe.Constants;
import com.java.chatbotbe.model.ChatbotDefautRespone;
import com.java.chatbotbe.model.ConversationModel;
import com.java.chatbotbe.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversation")
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @GetMapping("")
    public ChatbotDefautRespone<?> getConversation (){
        ChatbotDefautRespone<List<ConversationModel>> response = new ChatbotDefautRespone<>();
        try{
            List<ConversationModel> conversationModelList = conversationService.getConversation();
            response.setError(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            response.setDetailMessage(Constants.SUCCESS_MESSAGE);
            response.setOutput(conversationModelList);
        }catch (Exception e){
            response.setError(Constants.SERVER_ERROR_CODE);
            response.setMessage(Constants.SERVER_ERROR_MESSAGE);
            response.setDetailMessage(e.getMessage());
        }
        return response;
    }

    @PostMapping("/add")
    public ChatbotDefautRespone<?> addConversation (@RequestBody ConversationModel conversationModel){
        ChatbotDefautRespone<ConversationModel> response = new ChatbotDefautRespone<>();
        try{
            ConversationModel conversation = conversationService.save(conversationModel);
            response.setError(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            response.setDetailMessage(Constants.SUCCESS_MESSAGE);
            response.setOutput(conversation);
        }catch (Exception e){
            response.setError(Constants.SERVER_ERROR_CODE);
            response.setMessage(Constants.SERVER_ERROR_MESSAGE);
            response.setDetailMessage(e.getMessage());
        }
        return response;
    }
}
