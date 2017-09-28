package org.clojars.kyleannen.javaserver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.ArrayList;

public class ControllerFourOhFourTest {

  @Test
  void fourOhFourReturnsFourOhFourResponse() throws IOException {
    ArrayList<String> httpMessage = new ArrayList<>();
    httpMessage.add("GET /bad/test/route/where/is/this/going HTTP/1.1\r\n\r\n");
    RequestParameters requestParameters = new RequestParameters.RequestBuilder("/")
            .setRequestPath(httpMessage)
            .setHttpVerb(httpMessage)
            .setAccept(httpMessage)
            .setBodyContent(httpMessage)
            .setUserAgent(httpMessage)
            .setHost(httpMessage)
            .build();

    ControllerFourOhFour controllerFourOhFour = new ControllerFourOhFour();
    ResponseParameters responseParameters = controllerFourOhFour.getResponse(requestParameters);
    String actual = responseParameters.getResponseStatus();
    String expected = "HTTP/1.1 404 Not Found\r\n";
    assertEquals(expected, actual);
  }
}
