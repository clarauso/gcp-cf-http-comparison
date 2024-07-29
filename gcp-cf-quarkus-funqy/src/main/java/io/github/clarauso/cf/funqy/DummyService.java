package io.github.clarauso.cf.funqy;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DummyService {

  public DummyResource build(String value) {
    return new DummyResource(value);
  }
}
