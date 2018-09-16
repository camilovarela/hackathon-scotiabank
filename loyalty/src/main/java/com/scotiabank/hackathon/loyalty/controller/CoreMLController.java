package com.scotiabank.hackathon.loyalty.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.scotiabank.hackathon.loyalty.domain.ProductRecommenderResponse;
import com.scotiabank.hackathon.loyalty.domain.TxClassifierResponse;
import com.scotiabank.hackathon.loyalty.domain.TxFraudResponse;
import com.scotiabank.hackathon.loyalty.domain.TxGeoResponse;

@RestController
public class CoreMLController {

  @PostMapping(value = "/core-machine-learning/geolocalization")
  public ResponseEntity<TxGeoResponse> geolocalization() {
    return ResponseEntity.ok(new TxGeoResponse());
  }

  @PostMapping(value = "/core-machine-learning/recommender-motor")
  public ResponseEntity<ProductRecommenderResponse> recommender() {
    return ResponseEntity.ok(new ProductRecommenderResponse());
  }

  @PostMapping(value = "/core-machine-learning/fraud")
  public ResponseEntity<TxFraudResponse> fraud() {
    return ResponseEntity.ok(new TxFraudResponse());
  }

  @PostMapping(value = "/core-machine-learning/classification")
  public ResponseEntity<TxClassifierResponse> classification() {
    return ResponseEntity.ok(new TxClassifierResponse());
  }
}
