package org.clojars.kyleannen.javaserver;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

public class ResponseParametersTest {
  ServerUtils serverUtils;

  ResponseParametersTest() {
    this.serverUtils = new ServerUtils();
  }

  @Test
  void responseParameterContainsCorrectResponseStatus() {
    ResponseParameters responseParameters = new ResponseParameters.ResponseBuilder(200)
            .build();
    String expectedStatus = "HTTP/1.1 200 OK\r\n";
    String actaulStatus = responseParameters.getResponseStatus();
  }

  @Test
  void responseParametersSetCorrectContentLengthForFile() throws IOException {
    String testFile = System.getProperty("user.dir") + "/resources/404.html";
    File file = new File(testFile);
    String fileLength = Long.toString(file.length() + 2);
    ResponseParameters responseParameters = new ResponseParameters.ResponseBuilder(200)
            .setContentLength(testFile)
            .build();
    String expected = "Content-Length: " + fileLength  + "\r\n";
    String actual = responseParameters.getContentLength();
    assertEquals(expected, actual);
  }

  @Test
  void responseParametersSetsCorrectContentLengthForText() {
    String testString = "This is a test string\r\nThat is super cool";
    ResponseParameters responseParameters = new ResponseParameters.ResponseBuilder(200)
            .setContentLength(testString)
            .build();
    String expected = "Content-Length: " + this.serverUtils.getHttpHeaderContentLength(testString) + "\r\n";
    String actual = responseParameters.getContentLength();
    assertEquals(expected, actual);
  }

  @Test
  void responseParametersSetsCorrectContentTypeForFile() throws IOException {
    String file = System.getProperty("user.dir") + "/resources/404.html";
    ResponseParameters responseParameters = new ResponseParameters.ResponseBuilder(200)
            .setContentLength(file)
            .setContentType(file)
            .setDate()
            .setBodyType(file)
            .setBodyContent(file)
            .build();
    String expectedBodyType = "file";
    String actualBodyType = responseParameters.getBodyType();
    assertEquals(expectedBodyType, actualBodyType);

    String expectedContentType = "Content-Type: text/html\r\n";
    String actualContentType = responseParameters.getContentType();
    assertEquals(expectedContentType, actualContentType);
  }

  @Test
  void responseParametersDateFieldIsPresent() {
    ResponseParameters responseParameters = new ResponseParameters.ResponseBuilder(200)
            .setDate()
            .build();
    String expected = "Date:";
    String actual = responseParameters.getDate().split(" ")[0];
    assertEquals(expected, actual);
  }

  @Test
  void responseParmetersAccessControlSetsCorrectly() {
    ResponseParameters responseParameters = new ResponseParameters.ResponseBuilder(200)
            .setAccessControlAllowOrigin("api.testdomain.com")
            .build();
    String expected = "Access-Control-Allow-Origin: http://api.testdomain.com\r\n";
    String actual = responseParameters.getAccessControlAllowOrigin();
    assertEquals(expected, actual);
  }
}









