/*
 * Copyright 2017-2024 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.clarauso.cf.micronaut;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import io.micronaut.gcp.function.http.*;

public class DefaultFunctionTest {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void testWithValue() throws Exception {
    try (HttpFunction function = new HttpFunction()) {
      final var request =
          HttpRequest.GET("/default?value=test").contentType(MediaType.APPLICATION_JSON_TYPE);
      GoogleHttpResponse response = function.invoke(request);

      final var expectedResource = new DummyResource("test");
      final var resBody = objectMapper.readValue(response.getBodyAsBytes(), DummyResource.class);

      assertEquals(HttpStatus.OK, response.getStatus());
      assertEquals(expectedResource, resBody);
    }
  }

  @Test
  public void testNoValue() throws Exception {
    try (HttpFunction function = new HttpFunction()) {
      final var request = HttpRequest.GET("/default").contentType(MediaType.APPLICATION_JSON_TYPE);
      GoogleHttpResponse response = function.invoke(request);

      final var expectedResource = new DummyResource("default");
      final var resBody = objectMapper.readValue(response.getBodyAsBytes(), DummyResource.class);

      assertEquals(HttpStatus.OK, response.getStatus());
      assertEquals(expectedResource, resBody);
    }
  }
}
