import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class GetTest extends TestDirectorySetup {
  String directoryPath = System.getProperty("user.dir");


  GetTest() {
    ArrayList<String> httpMessage = new ArrayList<>();
  }

  @Test
  void getReturnsResultOfGetForDirectory() throws IOException {
    ArrayList<String> httpMessage = new ArrayList<>();
    httpMessage.add("GET /TestDirectory HTTP/1.1\r\n");
    RequestParameters validDirectoryRequest =
            new RequestParameters.RequestBuilder(directoryPath)
            .setRequestPath(httpMessage)
            .setHttpVerb(httpMessage)
            .build();

    Get testGet = new Get();
    ResponseParameters responseParams = testGet.get(validDirectoryRequest);
    String actualHttpCode = responseParams.responseHeader.get(0);

    String expectedHttpCode = "HTTP/1.1 200 OK\r\n";
    assertEquals(expectedHttpCode, actualHttpCode);

    String expectedBodyType = "text";
    String actualBodyType = responseParams.bodyType;
    assertEquals(expectedBodyType, actualBodyType);

    Boolean actualBodyContainsLink = responseParams.body.contains("<a href");
    assertEquals(true, actualBodyContainsLink);

    Boolean actualBodyContainsFile = responseParams.body.contains("testFile1.txt");
    assertEquals(true, actualBodyContainsFile);
  }

  @Test
  void getReturnsResultOfGetForBadDirectoryPath() throws IOException {
    ArrayList<String> httpMessage = new ArrayList<>();
    httpMessage.add("GET /TetDirectory HTTP/1.1\r\n");
    RequestParameters invalidDirectoryRequest =
            new RequestParameters.RequestBuilder(directoryPath)
                    .setRequestPath(httpMessage)
                    .setHttpVerb(httpMessage)
                    .build();

    Get testGet = new Get();
    ResponseParameters responseParams = testGet.get(invalidDirectoryRequest);

    String expectedHttpCode = "HTTP/1.1 404 Not Found\r\n\r\n";
    String actualHttpCode = responseParams.responseHeader.get(0);
    assertEquals(expectedHttpCode, actualHttpCode);
  }

  @Test
  void getReturnsResultOfGetForFilePath() throws IOException {
    ArrayList<String> httpMessage = new ArrayList<>();
    httpMessage.add("GET /TestDirectory/testFile1.txt HTTP/1.1\r\n");
    RequestParameters validFileRequest =
            new RequestParameters.RequestBuilder(directoryPath)
                    .setRequestPath(httpMessage)
                    .setHttpVerb(httpMessage)
                    .build();

    Get testGet = new Get();
    ResponseParameters responseParams = testGet.get(validFileRequest);

    String actualHttpCode = responseParams.responseHeader.get(0);
    String expectedHttpCode = "HTTP/1.1 200 OK\r\n";
    assertEquals(expectedHttpCode, actualHttpCode);

    String expectedBodyType = "file";
    String actualBodyType = responseParams.bodyType;
    assertEquals(expectedBodyType, actualBodyType);

    String expectedBody = System.getProperty("user.dir") + "/TestDirectory/testFile1.txt";
    String actualBody = responseParams.body;
    assertEquals(expectedBody, actualBody);
  }

  @Test
  void getReturnsResultOfPngGet() throws IOException {
    ArrayList<String> httpMessage = new ArrayList<>();
    httpMessage.add("GET /TestPng/test.png HTTP/1.1\r\n");
    RequestParameters validFileRequest =
            new RequestParameters.RequestBuilder(directoryPath)
                    .setRequestPath(httpMessage)
                    .setHttpVerb(httpMessage)
                    .build();

    Get testGet = new Get();
    ResponseParameters responseParams = testGet.get(validFileRequest);

    String actualHttpCode = responseParams.responseHeader.get(0);
    String expectedHttpCode = "HTTP/1.1 200 OK\r\n";
    assertEquals(expectedHttpCode, actualHttpCode);

    String expectedBodyType = "file";
    String actualBodyType = responseParams.bodyType;
    assertEquals(expectedBodyType, actualBodyType);

    String expectedBody = System.getProperty("user.dir") + "/TestPng/test.png";
    String actualBody = responseParams.body;
    assertEquals(expectedBody, actualBody);


  }
}