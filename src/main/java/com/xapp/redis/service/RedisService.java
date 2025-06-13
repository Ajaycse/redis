package com.xapp.redis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

  private final RedisTemplate redisTemplate;
  private final ObjectMapper mapper;
  private final ObjectMapper objectMapper;

  public <T> T get(String key, Class<T> type) {
    Object object = redisTemplate.opsForValue().get(key);
    if (object != null) {
      try {
        return mapper.readValue(object.toString(), type);
      } catch (Exception e) {
        throw new RuntimeException("Error converting value for key: " + key, e);
      }
    }
    return null;
  }

  public <T> void set(String key, T value, long timeout) {

    try {
      String stringValue = objectMapper.writeValueAsString(value);
      redisTemplate.opsForValue().set(key, stringValue, timeout, TimeUnit.SECONDS);
    } catch (Exception e) {
      throw new RuntimeException("Error setting value for key: " + key, e);
    }
  }

}
