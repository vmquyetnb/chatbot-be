package com.java.chatbotbe.repository;

import com.java.chatbotbe.entity.BotResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotResponseRepo extends JpaRepository<BotResponse,Long> {
}
