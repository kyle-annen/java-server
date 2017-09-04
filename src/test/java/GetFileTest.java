import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GetFileTest extends TestDirectorySetup {
  private RequestParameters requestParameters;

  GetFileTest() {
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
    GetFile getFile = new GetFile();
    String filePath = requestParameters.getDirectoryPath() + "/TestDirectory/testFile1.txt";
    ResponseParameters responseParameters = getFile.get(filePath);

    String actualFileType = responseParameters.getContentType();
    String expectedFileType = "Content-Type: text/plain";
    assertEquals(expectedFileType, actualFileType);

    String[] actualFilePath = responseParameters.getBodyContent().split("/");
    String actualFile = actualFilePath[actualFilePath.length - 1];
    String expectedFile = "testFile1.txt";
    assertEquals(expectedFile, actualFile);
  }
}