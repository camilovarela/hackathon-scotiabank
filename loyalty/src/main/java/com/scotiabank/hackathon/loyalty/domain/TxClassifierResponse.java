package com.scotiabank.hackathon.loyalty.domain;

import lombok.Data;

@Data
public class TxClassifierResponse {

  private Long transactionId;
  
  private String merchant;
  
  private String category;
}
