import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControllerFileTest extends TestDirectorySetup {
  private RequestParameters requestParameters;

  ControllerFileTest() {
    ArrayList<String> httpMessage = new ArrayList<>();
    httpMessage.add("GET /TestDirectory/testFile1.txt HTTP/1.1\r\n");
    String directoryPath = System.getProperty("user.dir");
    requestParameters = new RequestParameters.RequestBuilder(directoryPath)
            .setHttpVerb(httpMessage)
            .setRequestPath(httpMessage)
            .build();
  }

  @Test
  void getFileReturnsResponseWithCorrectFile() throws IOException {
    ControllerFile getFile = new ControllerFile();
    ResponseParameters responseParameters = getFile.getResponse(requestParameters);

    String actualFileType = responseParameters.getContentType();
    String expectedFileType = "Content-Type: text/plain";
    assertEquals(expectedFileType, actualFileType);

    String[] actualFilePath = responseParameters.getBodyContent().split("/");
    String actualFile = actualFilePath[actualFilePath.length - 1];
    String expectedFile = "testFile1.txt";
    assertEquals(expectedFile, actualFile);
  }
}