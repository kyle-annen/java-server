package org.clojars.kyleannen.javaserver;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

class ServerUtilsTest extends TestDirectorySetup {
  private ServerUtils utils = new ServerUtils();
  @Test
  void getHttpHeaderDate() {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat(
            "EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    String expectedDate = dateFormat.format(calendar.getTime());
    String actualDate = utils.getHttpHeaderDate();
    assertEquals(expectedDate, actualDate);
  }

  @Test
  void getHttpHeaderContentLength() {
    String testMessage = "THIS IS a test METHOD!\r\n";
    String actualLength = utils.getHttpHeaderContentLength(testMessage);
    String expectedLength = "24";
    assertEquals(actualLength, expectedLength);
  }

  @Test
  void getFileMimeTypeReturnsCorrectTypeTxt() throws IOException {
    String txtFilePath = System.getProperty("user.dir") + "/TestDirectory/testFile1.txt";
    String textFileTypeActual = utils.getFileMimeType(txtFilePath);
    String textFileTypeExpected = "text/plain";
    assertEquals(textFileTypeExpected, textFileTypeActual);
  }

  @Test
  void getFileMimeTypeReturnsCorrectTypePng() throws IOException {
    String txtFilePath = System.getProperty("user.dir") + "/TestPng/test.png";
    String textFileTypeActual = utils.getFileMimeType(txtFilePath);
    String textFileTypeExpected = "image/png";
    assertEquals(textFileTypeExpected, textFileTypeActual);
  }
}