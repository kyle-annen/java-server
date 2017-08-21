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
    Socket testSocket = new Socket();
    requestParameters = new RequestParameters.RequestBuilder(directoryPath)
            .setHttpVerb(httpMessage)
            .setRequestPath(httpMessage)
            .build();
  }



  @Test
  void getFileReturnsResponseWithCorrectFile() throws IOException {
    GetFile getFile = new GetFile();
    String filePath = requestParameters.getDirectoryPath() + "/TestDirectory/testFile1.txt";
    ResponseParameters responseParameters = getFile.get(requestParameters, filePath);

    String actualFileType = responseParameters.responseHeader.get(3);
    String expectedFileType = "ContentType: text/plain\r\n";
    assertEquals(expectedFileType, actualFileType);

    String[] actualFilePath = responseParameters.body.split("/");
    String actualFile = actualFilePath[actualFilePath.length - 1];
    String expectedFile = "testFile1.txt";
    assertEquals(expectedFile, actualFile);
  }
}