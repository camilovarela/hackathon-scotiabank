package com.scotiabank.hackathon.loyalty.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
  
  @PostMapping(value = "/payments/order")
  public ResponseEntity<String> orderProduct() {
    return ResponseEntity.ok("");
  }
}
