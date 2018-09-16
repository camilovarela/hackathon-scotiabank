package com.scotiabank.hackathon.loyalty.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.scotiabank.hackathon.loyalty.domain.CustomerRequest;
import com.scotiabank.hackathon.loyalty.domain.CustomerResponse;
import com.scotiabank.hackathon.loyalty.domain.Transaction;
import com.scotiabank.hackathon.loyalty.service.ProductService;
import com.scotiabank.hackathon.loyalty.service.SendGridService;
import com.scotiabank.hackathon.loyalty.utils.BuilderUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class CustomerController {

  private Map<Long, CustomerResponse> customers = new HashMap<>();
  
  private SendGridService mailService;
  
  private ProductService productService;
    
  /**
   * Constructor used for inject dependencies.
   * 
   * @param mailService {@link SendGridService} to be injected.
   */
  public CustomerController(SendGridService mailService, ProductService productService) {
    this.mailService = mailService;
    this.productService = productService;
    this.customers = BuilderUtil.buildInitialData();
  }

  @GetMapping(value = "/customers")
  @ApiOperation(value = "View a list of all customers")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved list"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource")})
  public ResponseEntity<List<CustomerResponse>> retrieveCustomers() {
    return ResponseEntity.ok(new ArrayList<>(this.customers.values()));
  }

  @PostMapping(value = "/customers")
  @ApiOperation(value = "Create a new customer")
  @ApiResponses(value = {@ApiResponse(code = 201, message = "Successfully created a customer"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource")})
  public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest payload) {

    CustomerResponse response = CustomerResponse.builder()
        .id((long) Math.abs(new Random().nextInt())).email(payload.getEmail())
        .fullName(payload.getFullName()).phoneNumber(payload.getPhoneNumber()).build();
    this.customers.put(response.getId(), response);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping(value = "/customers/{customerId}")
  @ApiOperation(value = "Retreive the information of an existing customer")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully operation"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 404, message = "Customer not found")})
  public ResponseEntity<CustomerResponse> retreiveCustomerInformation(
      @PathVariable("customerId") Long customerId) {

    CustomerResponse customer = this.customers.get(customerId);
    if (customer == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(customer);
  }

  @PutMapping(value = "/customers/{customerId}")
  @ApiOperation(value = "Update an existing customer")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully operation"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 404, message = "Customer not found")})
  public ResponseEntity<CustomerResponse> updateCustomer(
      @PathVariable("customerId") Long customerId, @RequestBody CustomerRequest payload) {

    CustomerResponse customer = this.customers.get(customerId);
    if (customer == null) {
      return ResponseEntity.notFound().build();
    }
    
    customer.setRedCoins(customer.getRedCoins() + payload.getRedCoins());
    if (payload.getEmail() != null) {
      customer.setEmail(payload.getEmail());
    }
    
    if (payload.getFullName() != null) {
      customer.setFullName(payload.getFullName());
    }
    
    if (payload.getPhoneNumber() != null) {
      customer.setPhoneNumber(payload.getPhoneNumber());
    }
    
    if (payload.getRedCoins() > 0) {
      this.mailService.sendEmailConfirmation(customer);
    }
    
    this.customers.put(customerId, customer);
    return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
  }

  @DeleteMapping(value = "/customers/{customerId}")
  @ApiOperation(value = "Delete an existing customer")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully operation"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 404, message = "Customer not found")})
  public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") Long customerId) {

    if (!this.customers.containsKey(customerId)) {
      return ResponseEntity.notFound().build();
    }
    this.customers.remove(customerId);
    return ResponseEntity.accepted().build();
  }
  
  @GetMapping(value = "/customers/{customerId}/transactions")
  @ApiOperation(value = "Retreives the information of all transactions done by a customer")
  public ResponseEntity<List<Transaction>> retreiveTransactions(@PathVariable("customerId") Long customerId) {
    return ResponseEntity.ok(new ArrayList<>());
  }
  
  @PostMapping(value = "/customers/{customerId}/products")
  public ResponseEntity<String> noticeCustomersForProduct(@RequestBody String product) {
    
    for (CustomerResponse customer : this.customers.values()) {
      this.productService.sendEmailConfirmation(customer);
    }
    return ResponseEntity.ok("");
  }
}
