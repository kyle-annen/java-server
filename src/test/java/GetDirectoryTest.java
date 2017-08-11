import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class GetDirectoryTest extends TestDirectorySetup {
  private RequestParameters requestParameters;

  GetDirectoryTest(){
    ArrayList<String> httpMessage = new ArrayList<>();
    httpMessage.add("GET /TestDirectory HTTP/1.1\r\n");
    String directoryPath = "./TestDirectory";
    Socket testSocket = new Socket();
    requestParameters = new RequestParameters(httpMessage, directoryPath, testSocket);
  }

  @Test
  void getDirectoryReturnsDirectoryContents() throws IOException {
    GetDirectory getDirectory = new GetDirectory(requestParameters);
    String directoryListing = getDirectory.getDirectoryListing("./TestDirectory");
    boolean containsFile = directoryListing.contains("testFile1.txt");
    boolean containsDirectory = directoryListing.contains("/TestDirectory");
    boolean containsFileLink = directoryListing.contains("<li><a href='/TestDirectory/testFile1.txt'>testFile1.txt</a></li>");

    assertEquals(containsFile, true);
    assertEquals(containsDirectory, true);
    assertEquals(containsFileLink, true);
  }

  @Test
  void filesListReturnsListOfFiles() throws IOException {
    GetDirectory getDirectory = new GetDirectory(requestParameters);
    ArrayList<String> fileList = getDirectory.filesList("./TestDirectory");
    String expected = "testFile1.txt";
    String actual = fileList.get(0);
    assertEquals(actual, expected);
  }

  @Test
  void formatDirectoryHtmlReturnsFormatedHtmlArrayList() throws IOException {
    GetDirectory getDirectory = new GetDirectory(requestParameters);
    ArrayList<String> fileList = getDirectory.filesList("./TestDirectory");
    ArrayList<String> formatedDirectory = getDirectory.formatDirectoryHtml(fileList);
    String docTypeActual = formatedDirectory.get(0);
    String docTypeExpected = "<!DOCTYPE html>\n";
    assertEquals(docTypeActual, docTypeExpected);

    String actualFileLink = formatedDirectory.get(9);
    String expectedFileLink = "<li><a href='/TestDirectory/testFile1.txt'>testFile1.txt</a></li>\n";
    assertEquals(actualFileLink, expectedFileLink);
  }

  @Test
  void getReturnsCorrectResponseParameter() throws IOException {
    GetDirectory getDirectory = new GetDirectory(requestParameters);
    ResponseParameters responseParams = getDirectory.get("./TestDirectory");
    assertEquals("HTTP/1.1 200 OK\r\n", responseParams.responseHeader.get(0));
    assertEquals("ContentLength: 210\r\n", responseParams.responseHeader.get(2));
    assertEquals("ContentType: text/html\r\n", responseParams.responseHeader.get(3));
    assertEquals("text", responseParams.bodyType);
    assertEquals(getDirectory.getDirectoryListing("./TestDirectory"), responseParams.body);
  }

  @Test
  void getReturnsCorrectResponseEmptyDir() throws IOException {
    GetDirectory getDirectory = new GetDirectory(requestParameters);
    ResponseParameters responseParams = getDirectory.get("./TestEmpty");
    assertEquals("HTTP/1.1 200 OK\r\n", responseParams.responseHeader.get(0));
    assertEquals("ContentLength: 178\r\n", responseParams.responseHeader.get(2));
    assertEquals("ContentType: text/html\r\n", responseParams.responseHeader.get(3));
    assertEquals(getDirectory.getDirectoryListing("./testEmpty"), responseParams.body);
    assertEquals(true, responseParams.body.contains("There are no files in this directory"));
  }

}




















