package com.scotiabank.hackathon.loyalty.domain;

import lombok.Data;

@Data
public class Transaction {
  
  private Long transactionId;
  
  private String date;
  
  private Long productId;
  
  private Long redCoins;
  
  private Double money;
}
