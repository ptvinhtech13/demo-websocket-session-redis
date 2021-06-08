package com.vinh.practice.websocket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstantMessage {
  private String text;
  private String from;
  @Builder.Default
  private Instant time = Instant.now();
}
