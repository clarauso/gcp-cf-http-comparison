package io.github.clarauso.cf.quarkus;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import io.quarkus.google.cloud.functions.test.FunctionType;
import io.quarkus.google.cloud.functions.test.WithFunction;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@WithFunction(FunctionType.HTTP)
@TestInstance(Lifecycle.PER_CLASS)
class HttpFunctionTestCase {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void testWithValue() throws JsonProcessingException {

    final var expected = objectMapper.writeValueAsString(new DummyResource("test"));

    when().get("/default?value=test").then().statusCode(200).body(is(expected));
  }

  @Test
  public void testNoValue() throws JsonProcessingException {

    final var expected = objectMapper.writeValueAsString(new DummyResource("default"));

    when().get("/default").then().statusCode(200).body(is(expected));
  }
}
