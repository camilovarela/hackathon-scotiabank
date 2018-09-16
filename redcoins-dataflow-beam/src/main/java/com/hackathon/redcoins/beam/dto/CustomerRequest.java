package com.hackathon.redcoins.beam.dto;

import lombok.Data;

@Data
public class CustomerRequest {
  
  private String fullName;
  
  private String email;
  
  private Long phoneNumber;
  
  private Long redCoins;
}
