package com.xapp.redis.controller.rest;


import com.xapp.redis.dto.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

  @PostMapping
  public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto order) {
    // Logic to create an order
    return ResponseEntity.ok(order);
  }

}
