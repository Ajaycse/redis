package com.xapp.redis.controller.rest;

import com.xapp.redis.dto.ApiResponse;
import com.xapp.redis.dto.ProductDto;
import com.xapp.redis.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<ProductDto> createOrder(@RequestBody ProductDto order) {
    return ResponseEntity.ok(productService.createProduct(order));
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<List<ProductDto>> getAllProducts() {
    List<ProductDto> products = productService.getAllProducts();
    return ApiResponse.create(products);
  }

  @GetMapping("/product/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<ProductDto> getProductById(@PathVariable Long id) throws NotFoundException {
    ProductDto product = productService.getProductById(id);
    return ApiResponse.create(product);
  }

}
