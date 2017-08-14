import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GetTest {
  private Get testGet;
  private ArrayList<String> rootRoute = new ArrayList<String>();
  private ArrayList<String> helloWorldRoute = new ArrayList<String>();
  private ArrayList<String> badRoute = new ArrayList<String>();
  private ArrayList<String> pingRoute = new ArrayList<String>();
  private ArrayList<String> imagePath = new ArrayList<String>();
  private ArrayList<String> imageTestPath = new ArrayList<String>();
  private ArrayList<String> imageFilePath = new ArrayList<String>();

  GetTest() {
    rootRoute.add("GET / HTTP/1.1\r\n\r\n");
    helloWorldRoute.add("GET /helloworld HTTP/1.1\r\n\r\n");
    badRoute.add("GET /that/this HTTP/1.1\r\n\r\n");
    pingRoute.add("GET /ping HTTP/1.1\r\n\r\n");
    imagePath.add("GET /images HTTP/1.1\r\n\r\n");
    imageTestPath.add("GET /images/test HTTP/1.1\r\n\r\n");
    imageFilePath.add("GET /helloworld.html HTTP/1.1\r\n");
  }

  @Test
  void getServesRootPathContent() throws ParseException, IOException {
    testGet = new Get();
    ArrayList<String> response = testGet.get(rootRoute);

    String actualRequestLine = response.get(0);
    String expectedRequestLine = "HTTP/1.1 200 OK\r\n";
    assertEquals(actualRequestLine, expectedRequestLine);
  }

//  @Test
//  void getServeHelloWorldContent() throws ParseException, IOException {
//    testGet = new Get();
//    ArrayList<String> response = testGet.get(helloWorldRoute);
//
//    String actualRequestLine = response.get(0);
//    String expectedRequestLine = "HTTP/1.1 200 OK\r\n";
//    assertEquals(actualRequestLine, expectedRequestLine);
//
//    String actualContent = response.get(6);
//    String expectedContent = "Hello world!\r\n";
//    assertEquals(actualContent, expectedContent);
//  }

  @Test
  void getServes404onBadPath() throws ParseException, IOException {
    testGet = new Get();
    ArrayList<String> response = testGet.get(badRoute);

    String actualRequestLine = response.get(0);
    String expectedRequestLine = "HTTP/1.1 404 Not Found\r\n";
    assertEquals(expectedRequestLine, actualRequestLine);
  }

//  @Test
//  void getServesPingPongContent() throws ParseException, IOException {
//    testGet = new Get();
//    ArrayList<String> response = testGet.get(pingRoute);
//
//    String actualResponseContent = response.get(6);
//    String expectedResponseContent = "pong\r\n";
//    assertEquals(actualResponseContent, expectedResponseContent);
//  }

  @Test
  void getServesImageDirectoryListing() throws IOException {
    testGet = new Get();
    ArrayList<String> response = testGet.get(imagePath);
    String actualResponse = response.get(0);
    String expectedResponse = "HTTP/1.1 200 OK\r\n";
    assertEquals(actualResponse, expectedResponse);
  }

  @Test
  void getServesImageTestDirectoryListing() throws IOException {
    testGet = new Get();
    ArrayList<String> response = testGet.get(imageTestPath);
    String actualResponse = response.get(0);
    String expectedResponse = "HTTP/1.1 200 OK\r\n";
    assertEquals(actualResponse, expectedResponse);
  }

  @Test
  void getServesFile() throws IOException {
    testGet = new Get();
    ArrayList<String> response = testGet.get(imageFilePath);
    String actualResponse = response.get(0);
    String expectedResponse = "HTTP/1.1 200 OK\r\n";
    assertEquals(expectedResponse, actualResponse);
  }
}