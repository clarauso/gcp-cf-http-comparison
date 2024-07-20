package io.github.clarauso.cf.micronaut;

import jakarta.inject.Singleton;

@Singleton
public class DummyService {

  public DummyResource build(String value) {
    return new DummyResource(value);
  }
}
