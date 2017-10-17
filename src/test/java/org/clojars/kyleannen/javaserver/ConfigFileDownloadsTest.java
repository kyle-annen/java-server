package org.clojars.kyleannen.javaserver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;

public class ConfigFileDownloadsTest {

  @Test
  void configFileIsDownloadableReturnsCorrectBoolean() {
    ArrayList<String> httpMessage = new ArrayList<>();
    httpMessage.add("GET /test.jpg HTTP/1.1");
    RequestParameters requestParameters =
            new RequestParameters.RequestBuilder("/")
            .setRequestPath(httpMessage)
            .build();
    ConfigFileDownloads configFileDownloads = new ConfigFileDownloads();
    Boolean actual =
            configFileDownloads.isDownloadable(requestParameters.getRequestPath());
    assertEquals(false, actual);
  }

  @Test
  void configFileIsNotDownloadableReturnsCorrectBoolean() {
    ArrayList<String> httpMessage = new ArrayList<>();
    httpMessage.add("GET /test.mp4 HTTP/1.1");
    RequestParameters requestParameters =
            new RequestParameters.RequestBuilder("/")
                    .setRequestPath(httpMessage)
                    .build();
    ConfigFileDownloads configFileDownloads = new ConfigFileDownloads();
    Boolean actual = configFileDownloads.isDownloadable(requestParameters.getRequestPath());
    assertEquals(true, actual);
  }

}
