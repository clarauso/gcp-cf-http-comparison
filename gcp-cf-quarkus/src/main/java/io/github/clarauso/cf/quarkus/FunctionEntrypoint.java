package io.github.clarauso.cf.quarkus;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Named("httpFunction")
@ApplicationScoped
public class FunctionEntrypoint implements HttpFunction {

  private static final String PARAM_KEY = "value";

  private static final String PARAM_DEFAULT = "default";

  private final DummyService service;

  private final ResponseSerializer serializer;

  public FunctionEntrypoint(DummyService service, ResponseSerializer serializer) {
    this.service = service;
    this.serializer = serializer;
  }

  @Override
  public void service(HttpRequest httpRequest, HttpResponse httpResponse) {
    final var inputValue =
        httpRequest.getQueryParameters().getOrDefault(PARAM_KEY, List.of(PARAM_DEFAULT)).getFirst();

    final var resource = service.build(inputValue);

    Optional.ofNullable(resource)
        .map(serializer::serializeObject)
        .ifPresent((String str) -> writeToResponse(httpResponse, str));
  }

  private static void writeToResponse(HttpResponse httpResponse, String s) {
    try {
      httpResponse.getWriter().write(s);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
