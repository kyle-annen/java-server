import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ResponseParametersTest {

  @Test
  void responseParametersContainsValuesFromConstruction() {
    ArrayList<String> responseHeader = new ArrayList<>();
    responseHeader.add("HTTP/1.1 404 Not Found\r\n\r\n");
    String bodyType = "text";
    String body = "<h1>404 not found</h1>";
    ResponseParameters responseParams = new ResponseParameters(responseHeader, bodyType, body);

    assertEquals(true, responseParams.responseHeader != null);
    assertEquals(true, responseParams.bodyType != null);
    assertEquals(true, responseParams.body != null);
  }
}