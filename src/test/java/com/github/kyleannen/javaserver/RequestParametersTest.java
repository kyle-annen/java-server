package com.github.kyleannen.javaserver;

import com.github.kyleannen.javaserver.RequestParameters;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RequestParametersTest {

  @Test
  void requestParametersContainsNeededValues() throws IOException {
    ArrayList<String> testMessage = new ArrayList<>();
    testMessage.add("GET / HTTP/1.1\r\n");
    testMessage.add("Host: localhost\r\n");
    testMessage.add("User-Agent: Mozilla\r\n");
    testMessage.add("Accept: text/html\r\n");
    String directoryPath = System.getProperty("user.dir");

    RequestParameters requestParams =
           new RequestParameters.RequestBuilder(directoryPath)
            .setHttpVerb(testMessage)
            .setRequestPath(testMessage)
            .setHost(testMessage)
            .setUserAgent(testMessage)
            .setAccept(testMessage)
            .build();
    String expectedVerb = "GET";
    String expectedRequestPath = "/";
    String expectedDirectoryPath = directoryPath;
    String expectedHost = "localhost";
    String expectedUserAgent = "Mozilla";
    String expectedAccept = "text/html";


    assertEquals(expectedVerb, requestParams.getHttpVerb());
    assertEquals(expectedRequestPath, requestParams.getRequestPath());
    assertEquals(expectedDirectoryPath, requestParams.getDirectoryPath());
    assertEquals(expectedHost, requestParams.getHost());
    assertEquals(expectedUserAgent, requestParams.getUserAgent());
    assertEquals(expectedAccept, requestParams.getAccept()[0].trim());
  }
}