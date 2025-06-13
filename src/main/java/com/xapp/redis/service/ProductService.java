package com.xapp.redis.service;

import com.xapp.redis.dto.ProductDto;
import com.xapp.redis.entity.Product;
import com.xapp.redis.mapper.ProductMapper;
import com.xapp.redis.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final RedisService redisService;

  public ProductDto createProduct(ProductDto productDto) {
    log.debug("Creating product: {}", productDto);
    Product product = productRepository.save(productMapper.toProduct(productDto));
    return productMapper.toProductDto(product);
  }

  public ProductDto getProductById(Long id) throws NotFoundException {
    ProductDto productDto = redisService.get("product:" + id, ProductDto.class);
    if (productDto != null) {
      log.debug("Product found in cache: {}", productDto);
      return productDto;
    }

    Product product = productRepository.findById(id)
        .orElseThrow(() -> new NotFoundException());
    productDto = productMapper.toProductDto(product);
    redisService.set("product:" + id, productDto, 3600);
    return productDto;
  }

public List<ProductDto> getAllProducts() {
      log.debug("Fetching all products");
      List<ProductDto> cachedProducts = redisService.get("products:all", List.class);
      if (cachedProducts != null) {
        log.debug("Products found in cache");
        return cachedProducts;
      }
      List<Product> products = productRepository.findAll();
      List<ProductDto> productDtos = products.stream()
          .map(productMapper::toProductDto)
          .toList();
      redisService.set("products:all", productDtos, 3600);
      return productDtos;
    }
}
