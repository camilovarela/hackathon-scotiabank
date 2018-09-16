package com.scotiabank.hackathon.loyalty.domain;

import lombok.Data;

@Data
public class TxFraudResponse {

  private Long transactionId;
  
  private double riskLevel;
  
  private String recommendation;
  
  private String cause;
}
