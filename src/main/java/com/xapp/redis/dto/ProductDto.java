package com.xapp.redis.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductDto {

  private String id;
  private String name;
  private String description;
  private Double price;
  @Enumerated(EnumType.STRING)
  private ProductCategory category;

}
