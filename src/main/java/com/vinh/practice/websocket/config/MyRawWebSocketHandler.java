package com.vinh.practice.websocket.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
public class MyRawWebSocketHandler implements WebSocketHandler {
  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {

    log.info("afterConnectionEstablished: Session Info: " + session.toString());
    TextMessage msg = new TextMessage("Client connection success!");

    //client will receive this frame as a callback to the success event
    session.sendMessage(msg);
  }

  @Override
  public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
  // this is the message content, that can be any format (json, xml, plain text... who knows?) System.out.println(message.getPayload());
    log.info("handleMessage Session Info: " + session.toString());
    TextMessage msg = new TextMessage("Message received. Thank you, client!");
    session.sendMessage(msg);
  }

  @Override
  public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
    log.info("afterConnectionClosed Session Info: " + session.toString());
  }

  @Override
  public boolean supportsPartialMessages() {
    return false;
  }
}
