package com.java.chatbotbe.repository;

import com.java.chatbotbe.entity.Conversation;
import com.java.chatbotbe.model.ConversationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepo extends JpaRepository<Conversation,Long> {

    @Query(value = "select c from Conversation c where c.id = :id")
    Conversation findConversationById(Long id);
}
