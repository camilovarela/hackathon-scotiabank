package com.scotiabank.hackathon.loyalty.domain;

import lombok.Data;

@Data
public class TxGeoResponse {

  private Long transactionId;
  
  private String merchant;
  
  private Double latitude;
  
  private Double longitude;
  
  private String companyLink;
  
  private String companyContact;
}
