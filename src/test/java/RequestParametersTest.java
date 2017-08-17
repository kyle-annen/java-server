import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RequestParametersTest {

  @Test
  void requestParametersContainsNeededValues() throws IOException {
    ArrayList<String> testMessage = new ArrayList<>();
    testMessage.add("GET / HTTP/1.1\r\n\r\n");
    String testDirectoryPath = System.getProperty("user.dir");

    RequestParameters requestParameters = new RequestParameters(testMessage, testDirectoryPath);

    assertEquals(true, requestParameters.httpMessage != null);
    assertEquals(true, requestParameters.directoryPath != null);

  }
}