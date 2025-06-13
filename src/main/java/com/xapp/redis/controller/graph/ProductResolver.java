package com.xapp.redis.controller.graph;


import com.xapp.redis.dto.ApiResponse;
import com.xapp.redis.dto.ProductDto;
import com.xapp.redis.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ProductResolver {

  private final ProductService productService;

  @QueryMapping("getAllProducts")
  public ApiResponse<List<ProductDto>> getAllProducts() {
    List<ProductDto> products = productService.getAllProducts();
    return ApiResponse.create(products);
  }

  @QueryMapping("getProductById")
  public ApiResponse<ProductDto> getProductByIdt(@Argument("id") Long productId)
      throws NotFoundException {
    ProductDto product = productService.getProductById(productId);
    return ApiResponse.create(product);
  }

}
