package io.github.clarauso.cf.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class CloudFunctionMain {

  public static void main(String[] args) {
    SpringApplication.run(CloudFunctionMain.class, args);
  }

  @Bean
  public Function<Void, DummyResource> getDummy() {
    return r -> new DummyResource("default");
  }
}
