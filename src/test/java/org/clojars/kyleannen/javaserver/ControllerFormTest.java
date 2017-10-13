package org.clojars.kyleannen.javaserver;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerFormTest {

  @Test
  void controllerFormReturnsResponseRequestForFormIndex() throws IOException {
    ArrayList<String> httpMessage = new ArrayList<>();
    httpMessage.add("GET /form HTTP/1.1\r\n\r\n");
    RequestParameters requestParameters =
            new RequestParameters.RequestBuilder("")
                    .setRequestPath(httpMessage)
                    .setHttpVerb(httpMessage)
                    .build();
    ControllerForm controllerForm = new ControllerForm();
    ResponseParameters responseParameters = controllerForm.getResponse(requestParameters);
    assertEquals("/resources/form/index.html",responseParameters.getBodyContent());
    assertEquals("Content-Type: text/html\r\n", responseParameters.getContentType());
  }
}
