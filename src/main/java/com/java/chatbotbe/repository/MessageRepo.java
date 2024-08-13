package com.java.chatbotbe.repository;

import com.java.chatbotbe.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Message,Long> {
    @Query(value = "select m from Message m where m.conversation.id = :conversationId")
    List<Message> findMessagesByConversationId(Long conversationId);
}
