package io.github.clarauso.cf.funqy;

import io.quarkus.funqy.Funq;

import java.util.Optional;

public class FunctionEntrypoint {

  private static final String PARAM_DEFAULT = "default";

  private final DummyService service;

  public FunctionEntrypoint(DummyService service) {
    this.service = service;
  }

  @Funq
  public DummyResource dummy(DummyParams params) {
    final var inputValue = Optional.ofNullable(params.getValue()).orElse(PARAM_DEFAULT);
    return service.build(inputValue);
  }
}
