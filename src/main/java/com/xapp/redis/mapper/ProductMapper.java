package com.xapp.redis.mapper;

import com.xapp.redis.dto.ProductDto;
import com.xapp.redis.entity.Product;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProductMapper {

  ProductDto toProductDto(Product product);
  Product toProduct(ProductDto productDto);
}
