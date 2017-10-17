package org.clojars.kyleannen.javaserver;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerOptionsTest {

  @Test
  void controllerOptionsRespondsWithOptionsIfRouteExists() throws IOException {
    ArrayList<String> HttpMessage = new ArrayList<>();
    HttpMessage.add("OPTIONS / HTTP/1.1\r\n");
    HttpMessage.add("Host: test.host\r\n");
    HttpMessage.add("Accept: text/html,application/xhtml+xml,application/xml\r\n");
    HttpMessage.add("Accept-Language: en-us,en\r\n");
    HttpMessage.add("Connection: keep-alive\r\n");
    HttpMessage.add("Origin: http://foo.example\r\n");
    HttpMessage.add("Access-Control-Request-Method: POST\r\n");

    RequestParameters requestParameters =
            new RequestParameters.RequestBuilder(System.getProperty("user.dir"))
                    .setHttpVerb(HttpMessage)
                    .setRequestPath(HttpMessage)
                    .build();

    ResponseParameters responseParameters = new ControllerOptions().getResponse(requestParameters);

    String actual = responseParameters.getAccessControlAllowOrigin();
    String expected = "Access-Control-Allow-Origin: *\r\n";

    assertEquals(expected, actual);
  }
}
