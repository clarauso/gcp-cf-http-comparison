package io.github.clarauso.cf.micronaut;

import io.micronaut.http.annotation.*;
import jakarta.annotation.Nullable;

import java.util.Optional;

@Controller("/default")
public class DefaultController {

  private static final String PARAM_DEFAULT = "default";

  private final DummyService dummyService;

  public DefaultController(DummyService dummyService) {
    this.dummyService = dummyService;
  }

  @Get
  public DummyResource getDummy(@QueryValue @Nullable String value) {
    final var paramOrDefault = Optional.ofNullable(value).orElse(PARAM_DEFAULT);
    return dummyService.build(paramOrDefault);
  }
}
