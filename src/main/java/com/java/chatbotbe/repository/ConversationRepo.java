package com.java.chatbotbe.repository;

import com.java.chatbotbe.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepo extends JpaRepository<Conversation,Long> {

    Conversation findConversationById(Long id);

    @Query("select c from Conversation c where c.user.id = :id")
    List<Conversation> findConversationByUserId (Long id);
}
