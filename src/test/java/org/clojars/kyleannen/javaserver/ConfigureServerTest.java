package org.clojars.kyleannen.javaserver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfigureServerTest {

  @Test
  public void configureServerReturnsAServerWithPortConfiguredWhenPortPassed() {
    String testPort = "3333";
    String[] args = new String[]{"-p", testPort};
    Router testRouter = new Router();
    Server testServer = new ConfigureServer().configure(args, testRouter);
    assertEquals(Integer.parseInt(testPort), testServer.getPortNumber());
  }

  @Test
  public void configureServerReturnsAServerWithDirectoryConfiguredWhenDirectoryPassed() {
    String testDirectory = System.getProperty("user.dir") + "/resources";
    String[] args = new String[]{"-d", testDirectory};
    Router router = new Router();
    Server testServer = new ConfigureServer().configure(args, router);
    assertEquals(testDirectory, testServer.getDirectoryPath());
  }
}
