import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.net.Socket;


class GetHtmlTextTest extends TestDirectorySetup {
  private RequestParameters requestParameters;


  GetHtmlTextTest(){
    ArrayList<String> httpMessage = new ArrayList<>();
    httpMessage.add("GET /TestDirectory HTTP/1.1\r\n");
    String directoryPath = "./TestDirectory";
    Socket testSocket = new Socket();
    requestParameters = new RequestParameters(httpMessage, directoryPath);
  }

  @Test
  void getFileContentsReturnsFileContents() {
    String fullFilePath = System.getProperty("user.dir") + "/TestDirectory/testFile1.txt";
    GetHtmlText getHtmlText = new GetHtmlText();
    String actualFileContents = getHtmlText.getFileContents(fullFilePath);
    String expectedFileContents = "testtesttest";
    assertEquals(expectedFileContents, actualFileContents);

  }

}