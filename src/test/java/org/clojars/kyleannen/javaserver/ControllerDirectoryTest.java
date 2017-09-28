package org.clojars.kyleannen.javaserver;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ControllerDirectoryTest extends TestDirectorySetup {
  private RequestParameters requestParameters;

  ControllerDirectoryTest(){
    ArrayList<String> httpMessage = new ArrayList<>();
    httpMessage.add("GET /TestDirectory HTTP/1.1\r\n");
    String directoryPath = "./TestDirectory";
    requestParameters = new RequestParameters.RequestBuilder(directoryPath)
            .setHttpVerb(httpMessage)
            .setRequestPath(httpMessage)
            .build();
  }

  @Test
  void getDirectoryReturnsDirectoryContents() throws IOException {
    ControllerDirectory getDirectory = new ControllerDirectory();
    String directoryListing = getDirectory.getDirectoryListing("./TestDirectory", requestParameters.getRequestPath());
    boolean containsFile = directoryListing.contains("testFile1.txt");
    boolean containsDirectory = directoryListing.contains("/TestDirectory");
    boolean containsFileLink = directoryListing.contains("<li><a href='/TestDirectory/testFile1.txt'>testFile1.txt</a></li>");

    assertEquals(containsFile, true);
    assertEquals(containsDirectory, true);
    assertEquals(containsFileLink, true);
  }

  @Test
  void filesListReturnsListOfFiles() throws IOException {
    ControllerDirectory getDirectory = new ControllerDirectory();
    ArrayList<String> fileList = getDirectory.filesList("./TestDirectory");
    String expected = "testFile1.txt";
    String actual = fileList.get(0);
    assertEquals(actual, expected);
  }

  @Test
  void formatDirectoryHtmlReturnsFormatedHtmlArrayList() throws IOException {
    ControllerDirectory getDirectory = new ControllerDirectory();
    ArrayList<String> fileList = getDirectory.filesList("./TestDirectory");
    ArrayList<String> formatedDirectory = getDirectory.formatDirectoryHtml(fileList, requestParameters.getRequestPath());
    String docTypeActual = formatedDirectory.get(0);
    String docTypeExpected = "<!DOCTYPE html>\n";
    assertEquals(docTypeActual, docTypeExpected);

    String actualFileLink = formatedDirectory.get(9);
    String expectedFileLink = "<li><a href='/TestDirectory/testFile1.txt'>testFile1.txt</a></li>\n";
    assertEquals(actualFileLink, expectedFileLink);
  }

  @Test
  void getReturnsCorrectResponseEmptyDir() throws IOException {
    ArrayList<String> httpMessage2 = new ArrayList<>();
    httpMessage2.add("GET /testEmpty HTTP/1.1\r\n");
    String directoryPath = "./testEmpty";
    requestParameters = new RequestParameters.RequestBuilder(directoryPath)
            .setHttpVerb(httpMessage2)
            .setRequestPath(httpMessage2)
            .build();
    ControllerDirectory controllerDirectory = new ControllerDirectory();
    ResponseParameters responseParams = controllerDirectory.getResponse(requestParameters);
    assertEquals("HTTP/1.1 200 OK\r\n", responseParams.getResponseStatus());
    assertEquals("Content-Length: 174\r\n", responseParams.getContentLength());
    assertEquals("Content-Type: text/html\r\n", responseParams.getContentType());
    assertEquals(controllerDirectory.getDirectoryListing("./testEmpty", requestParameters.getRequestPath()), responseParams.getBodyContent());
    assertEquals(true, responseParams.getBodyContent().contains("There are no files in this directory"));
  }

}




















