package com.java.chatbotbe.controller;

import com.java.chatbotbe.Constants;
import com.java.chatbotbe.entity.Message;
import com.java.chatbotbe.model.ChatbotDefautRespone;
import com.java.chatbotbe.model.MessageModel;
import com.java.chatbotbe.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;


    @PostMapping("/send")
    public ChatbotDefautRespone<?> sendMessage(@RequestParam Long conversationId,
                                               @RequestBody MessageModel messageModel) {
        ChatbotDefautRespone<MessageModel> respone = new ChatbotDefautRespone<>();
        try{
            MessageModel message = messageService.sendMessage(conversationId, messageModel);
            respone.setError(Constants.SUCCESS_CODE);
            respone.setMessage(Constants.SUCCESS_MESSAGE);
            respone.setDetailMessage(Constants.SUCCESS_MESSAGE);
            respone.setOutput(message);
        }catch (Exception e){
            respone.setError(Constants.SERVER_ERROR_CODE);
            respone.setMessage(Constants.SERVER_ERROR_MESSAGE);
            respone.setDetailMessage(e.getMessage());
        }
        return respone;
    }
}
