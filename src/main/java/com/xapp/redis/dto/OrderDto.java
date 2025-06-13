package com.xapp.redis.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class OrderDto {

  private Long id;
  private Long productId;
  private int quantity;
}
