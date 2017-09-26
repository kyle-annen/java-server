package com.github.kyleannen.javaserver;

import com.github.kyleannen.javaserver.ReadRequest;
import com.github.kyleannen.javaserver.RequestParameters;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ReadRequestTest {

  class MockSocket extends Socket {
    @Override
    public InputStream getInputStream() throws IOException {
      return new ByteArrayInputStream("GET / HTTP/1.1\r\n\r\n".getBytes());
    }
  }

  @Test
  void readRequestReturnsRequestParameters() throws IOException {
    ReadRequest readRequest = new ReadRequest();
    MockSocket mockSocket = new MockSocket();
    RequestParameters requestParameters = readRequest.getRequest(mockSocket, "/");
    String actual = requestParameters.getHttpVerb();
    String expected = "GET";
    assertEquals(expected, actual);

    String actualPath = requestParameters.getRequestPath();
    String expectedPath = "/";
    assertEquals(expectedPath, actualPath);
  }
}
