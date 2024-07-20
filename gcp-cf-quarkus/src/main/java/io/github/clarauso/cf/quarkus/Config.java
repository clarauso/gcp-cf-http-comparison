package io.github.clarauso.cf.quarkus;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Config {

  @ApplicationScoped
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}
