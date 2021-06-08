package com.vinh.practice.websocket;

import com.vinh.practice.websocket.dto.InstantMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Random;

@Slf4j
@Controller
@EnableScheduling
public class ChatController {

  private final List<String> publishChannels = List.of("/topic/public.messages");

  @Autowired private SimpMessagingTemplate simpMessagingTemplate;

  @MessageMapping("/send.message")
  public void sendPublicMessage(InstantMessage instantMessage) {
    log.info("sendPublicMessage: " + instantMessage);
    publishAllChannels(instantMessage);
  }

  private void publishAllChannels(InstantMessage instantMessage) {
    publishChannels.forEach(it -> simpMessagingTemplate.convertAndSend(it, instantMessage));
  }

  private Random random = new Random();

  @Scheduled(fixedRate = 5000)
  public void severSend() {
    final InstantMessage instantMessage =
        InstantMessage.builder()
            .from("Server")
            .text("Server Send Text: " + random.nextInt(1000))
            .build();
    log.info("severSend String: " + instantMessage.toString());
    publishAllChannels(instantMessage);
  }
}
