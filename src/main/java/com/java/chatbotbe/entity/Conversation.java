package com.java.chatbotbe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "conversation")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @CreatedDate
    @Column(updatable = false, name = "created_date")
    private LocalDateTime createdDate;


    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    private List<Message> message;
}
