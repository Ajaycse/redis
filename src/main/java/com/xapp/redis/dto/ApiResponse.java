package com.xapp.redis.dto;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

  private static final String TOTAL_ELEMENTS_KEY = "totalElements";
  private static final String TOTAL_PAGES_KEY = "totalPages";
  private static final String NUMBER_OF_ELEMENTS_KEY = "numberOfElements";
  private static final String FIRST_KEY = "first";
  private static final String LAST_KEY = "last";

  private final T data;
  private final Map<String, Object> meta;

  @JsonCreator
  public ApiResponse(@JsonProperty("data") T data) {
    this.data = data;
    this.meta = null;
  }

  public ApiResponse(T data, Page<?> page) {
    this.data = data;
    this.meta = Map.of(TOTAL_PAGES_KEY, page.getTotalPages(), TOTAL_ELEMENTS_KEY,
        page.getTotalElements(), NUMBER_OF_ELEMENTS_KEY, page.getNumberOfElements(), FIRST_KEY,
        page.isFirst(), LAST_KEY, page.isLast());
  }

  public static <T> ApiResponse<T> create(T data) {
    return new ApiResponse<>(data);
  }

  @SuppressWarnings("unchecked")
  public static <T extends List<H>, H> ApiResponse<T> create(Page<H> page) {
    return new ApiResponse<>((T) page.getContent(), page);
  }

  public static <T, H> ApiResponse<T> create(T data, Page<H> page) {
    return new ApiResponse<>(data, page);
  }

  public T getData() {
    return data;
  }

  public Map<String, Object> getMeta() {
    return meta;
  }
}
