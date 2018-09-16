package com.hackathon.redcoins.beam.constant;

import lombok.Getter;

public enum DataflowState {

  READ_FROM_PUBSUB("PubSub [%s]"),

  JSON_TO_DTO("Convert to List<Transaction>");

  @Getter
  private String description;

  private DataflowState(String description) {
    this.description = description;
  }
}
