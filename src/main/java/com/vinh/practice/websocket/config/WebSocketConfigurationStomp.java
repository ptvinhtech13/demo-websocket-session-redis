package com.vinh.practice.websocket.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.Session;
import org.springframework.session.web.socket.config.annotation.AbstractSessionWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@Configuration
@EnableScheduling
@EnableWebSocketMessageBroker
public class WebSocketConfigurationStomp
    extends AbstractSessionWebSocketMessageBrokerConfigurer<Session> {

  @Override
  public void configureMessageBroker(final MessageBrokerRegistry config) {
    config.enableSimpleBroker("/queue/", "/topic");
    config.setApplicationDestinationPrefixes("/app");
  }

  @Override
  protected void configureStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/stompwebsocket");
    registry.addEndpoint("/messages").withSockJS();
  }

  @EventListener
  public void onSocketConnected(SessionConnectedEvent event) {
    StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
    log.info("WebSocket Session Connected: {}", event.getMessage());
    log.info("Connect event [sessionId: {} ]", sha.getSessionId());
  }

  @EventListener
  public void onSocketDisconnected(SessionDisconnectEvent event) {
    StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
    log.info("WebSocket Session Disconnected: {}", event.getMessage());
    log.info("DisConnect event [sessionId: {}]", sha.getSessionId());
  }
}
