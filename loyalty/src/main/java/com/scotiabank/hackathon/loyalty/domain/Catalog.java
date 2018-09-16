package com.scotiabank.hackathon.loyalty.domain;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Catalog {
  
  private String key;
  
  private String title;
  
  private String description;
  
  private List<Product> products;
}
