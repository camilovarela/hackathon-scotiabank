package com.scotiabank.hackathon.loyalty.domain;

import lombok.Data;

@Data
public class Product {
  
  private String productKey;
  
  private String name;
  
  private String description;
  
  private Long redCoins;
  
  private Double midRedCoins;
  
  private Double midPrice;
  
  private Integer quantity;
}
