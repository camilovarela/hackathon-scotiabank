package com.scotiabank.hackathon.loyalty.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.scotiabank.hackathon.loyalty.domain.Catalog;
import com.scotiabank.hackathon.loyalty.domain.CatalogRequest;
import com.scotiabank.hackathon.loyalty.domain.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api
public class CatalogController {

  private Map<String, Catalog> catalogs;

  @GetMapping(value = "/catalogs")
  @ApiOperation(value = "View a list of all catalogs")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved list"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource")})
  public ResponseEntity<List<Catalog>> retrieveCustomers() {
    return ResponseEntity.ok(new ArrayList<>(this.catalogs.values()));
  }

  @PostMapping(value = "/catalogs")
  @ApiOperation(value = "Create a new catalog")
  @ApiResponses(value = {@ApiResponse(code = 201, message = "Successfully created a catalog"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource")})
  public ResponseEntity<Catalog> createCatalog(@RequestBody CatalogRequest payload) {

    Catalog response = Catalog.builder().key(payload.getKey()).title(payload.getTitle())
        .description(payload.getDescription()).build();
    this.catalogs.put(payload.getKey(), response);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping(value = "/catalogs/{key}")
  @ApiOperation(value = "Retreive the information of an existing catalog")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully operation"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 404, message = "Catalog not found")})
  public ResponseEntity<Catalog> retreiveCustomerInformation(@PathVariable("key") String key) {

    Catalog catalog = this.catalogs.get(key);
    if (catalog == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(catalog);
  }

  @PutMapping(value = "/catalogs/{key}")
  @ApiOperation(value = "Update an existing catalog")
  @ApiResponses(value = {@ApiResponse(code = 202, message = "Successfully operation"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 404, message = "Catalog not found")})
  public ResponseEntity<Catalog> updateCustomer(@PathVariable("key") String key,
      @RequestBody CatalogRequest payload) {

    Catalog catalog = this.catalogs.get(key);
    if (catalog == null) {
      return ResponseEntity.notFound().build();
    }

    catalog = Catalog.builder().key(key).title(payload.getTitle())
        .description(payload.getDescription()).build();
    this.catalogs.put(key, catalog);
    return new ResponseEntity<>(catalog, HttpStatus.ACCEPTED);
  }

  @DeleteMapping(value = "/catalogs/{key}")
  @ApiOperation(value = "Delete an existing catalog")
  @ApiResponses(value = {@ApiResponse(code = 202, message = "Successfully operation"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 404, message = "Catalog not found")})
  public ResponseEntity<Void> deleteCustomer(@PathVariable("key") String key) {

    if (!this.catalogs.containsKey(key)) {
      return ResponseEntity.notFound().build();
    }
    this.catalogs.remove(key);
    return ResponseEntity.accepted().build();
  }

  @GetMapping(value = "/catalogs/{key}/products")
  @ApiOperation(value = "View a list of all products of given catalog")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved list"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource")})
  public ResponseEntity<List<Product>> retrieveProducts(@PathVariable("key") String key) {
    return ResponseEntity.ok(new ArrayList<>(this.catalogs.get(key).getProducts()));
  }

  @PostMapping(value = "/catalogs/{key}/products")
  @ApiOperation(value = "Create a new product into a given catalog")
  public ResponseEntity<Product> createProduct(@PathVariable("key") String key) {
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping(value = "/catalogs/{key}/products/{productId}")
  @ApiOperation(value = "Retreive the product information")
  public ResponseEntity<Product> retreiveProduct(@PathVariable("key") String key,
      @PathVariable("productId") String productId) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping(value = "/catalogs/{key}/products/{productId}")
  @ApiOperation(value = "Update the product given its catalog and its identification")
  public ResponseEntity<Product> updateProduct(@PathVariable("key") String key,
      @PathVariable("productId") String productId) {
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  @DeleteMapping(value = "/catalogs/{key}/products/{productId}")
  @ApiOperation(value = "Delete a product given its catalog and its identification")
  public ResponseEntity<Product> deleteProduct(@PathVariable("key") String key,
      @PathVariable("productId") String productId) {
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }
}
