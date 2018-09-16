package com.scotiabank.hackathon.loyalty.domain;

import lombok.Data;

@Data
public class CustomerRequest {
  
  private String fullName;
  
  private String email;
  
  private Long phoneNumber;
  
  private long redCoins;
}
