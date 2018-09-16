package com.scotiabank.hackathon.loyalty.service;

import lombok.Data;

@Data
public class ContactInfo {

  private boolean acceptInvitation;

  private String fullName;

  private String message;
}
