package com.hackathon.redcoins.beam.dto;

import org.apache.beam.sdk.coders.AvroCoder;
import org.apache.beam.sdk.coders.DefaultCoder;
import lombok.Data;

@Data
@DefaultCoder(AvroCoder.class)
public class Transaction {
  
  private Long cardNumber;
  
  private Long customerId;
  
  private Double amount;
  
  private String merchant;
  
  private String date;
}