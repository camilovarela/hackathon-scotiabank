package com.scotiabank.hackathon.loyalty.domain;

import lombok.Data;

@Data
public class CatalogRequest {
  
  private String key;
  
  private String title;
  
  private String description;
}
