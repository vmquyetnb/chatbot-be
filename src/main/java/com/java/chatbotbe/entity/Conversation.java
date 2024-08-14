package com.java.chatbotbe.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.java.chatbotbe.util.LocalDateTimeDeserializer;
import com.java.chatbotbe.util.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;;

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
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdDate;


//    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
//    private List<Message> message;

}
