package com.scotiabank.hackathon.loyalty.domain;

import java.util.List;
import lombok.Data;

@Data
public class ProductRecommenderResponse {
  
  private List<Product> products;
  
  private List<Double> matchPercentage;
}
