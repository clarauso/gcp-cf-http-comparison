package io.github.clarauso.cf.quarkus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResponseSerializer {

  private final ObjectMapper objectMapper;

  public ResponseSerializer(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public String serializeObject(Object o) {
    try {
      return objectMapper.writeValueAsString(o);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
