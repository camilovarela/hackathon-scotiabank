package com.scotiabank.hackathon.loyalty.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {

  private Long id;

  private String fullName;

  private String email;

  private Long phoneNumber;
  
  private long redCoins;
}
