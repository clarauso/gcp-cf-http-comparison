package io.github.clarauso.cf.spring;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = {"local.server.port=8080"})
public class HttpFunctionTestCase {

  private static Process emulatorProcess = null;

  @BeforeEach
  public void setUp() throws IOException {
    // Get the sample's base directory (the one containing a pom.xml file)
    String baseDir = System.getProperty("user.dir");

    // Emulate the function locally by running the Functions Framework Maven plugin
    emulatorProcess =
        new ProcessBuilder().command("mvn", "function:run").directory(new File(baseDir)).start();
  }

  @AfterAll
  public static void tearDown() throws IOException {
    // Display the output of the plugin process
    InputStream stdoutStream = emulatorProcess.getInputStream();
    ByteArrayOutputStream stdoutBytes = new ByteArrayOutputStream();
    stdoutBytes.write(stdoutStream.readNBytes(stdoutStream.available()));
    System.out.println(stdoutBytes.toString(StandardCharsets.UTF_8));

    // Terminate the running Functions Framework Maven plugin process (if it's still running)
    if (emulatorProcess.isAlive()) {
      emulatorProcess.destroy();
    }
  }

  @LocalServerPort private int port;

  private TestRestTemplate rest = new TestRestTemplate();

  @Test
  public void testSample() {
    final var endpoint = "http://localhost:" + port;

    final var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    DummyResource result = rest.postForObject(endpoint, null, DummyResource.class);
    final var expected = new DummyResource("default");
    Assertions.assertEquals(expected, result);
  }
}
