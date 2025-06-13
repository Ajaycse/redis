package com.xapp.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisServiceTest {

  @Autowired
  private RedisTemplate redisTemplate;

  @Test
  public void testRedisConnection() {
    // Test if Redis is connected by setting and getting a value
    redisTemplate.opsForValue().set("name", "ajay");
    String value = (String) redisTemplate.opsForValue().get("name");
    String salary = (String) redisTemplate.opsForValue().get("salary");

    // Assert that the value is as expected
    assert "ajay".equals(value) : "Expected 'testValue', but got: " + value;
  }



}
