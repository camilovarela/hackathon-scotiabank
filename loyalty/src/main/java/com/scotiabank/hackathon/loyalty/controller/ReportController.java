package com.scotiabank.hackathon.loyalty.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

  @PostMapping(value = "/report/transactions")
  public ResponseEntity<String> geolocalization() {
    return ResponseEntity.ok("");
  }
  
  @PostMapping(value = "/report/redcoins-historic")
  public ResponseEntity<String> redcoinsHistoric() {
    return ResponseEntity.ok("");
  }
  
  @PostMapping(value = "/report/customers")
  public ResponseEntity<String> customersHistoric() {
    return ResponseEntity.ok("");
  }
  
  @PostMapping(value = "/report/products")
  public ResponseEntity<String> productsHistoric() {
    return ResponseEntity.ok("");
  }
}
