package com.java.chatbotbe.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageModel {
    private String content;
    private String sender;
    private LocalDateTime sentTime;
}
