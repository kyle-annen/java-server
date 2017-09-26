package com.github.kyleannen.javaserver;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class RequestHandlerTest extends TestDirectorySetup {
  private Integer startPort1 = 2020;

  LoggerInterface logger = new LoggerInterface() {
    @Override
    public void log(String string) {
    }
  };


  @Test
  void serverSendsValidResponseToValidRequest() throws InterruptedException, IOException {

    try {
      String port = startPort1.toString();
      String[] serverResponse;
      ExecutorService requestExecutor = Executors.newFixedThreadPool(1);
      Server testServer = new Server(
              new String[]{"-p", port},
              requestExecutor,
              new ReadRequest(),
              new SendResponse(),
              new Router(),
              logger);
      Thread testServerThread = new Thread(testServer);
      testServerThread.start();
      Thread.sleep(100);
      Socket testSocket = new Socket("localhost", Integer.parseInt(port));
      DataOutputStream sendToServer =
              new DataOutputStream(testSocket.getOutputStream());
      BufferedReader readFromServer =
              new BufferedReader(new InputStreamReader(testSocket.getInputStream()));
      sendToServer.writeBytes("GET / HTTP/1.1\r\n\r\n");
      sendToServer.flush();
      serverResponse = readFromServer.readLine().split(" ");
      String actualResponse = String.join(" ", serverResponse);
      String expectedResponse = "HTTP/1.1 200 OK";
      testServer.stop();
      assertEquals(actualResponse, expectedResponse);
    } catch (IOException ignore) {
      startPort1 += 1;
      this.serverSendsValidResponseToValidRequest();
    }
  }

  @Test
  void serverSendsValidResponseToValidRequestForFile() throws InterruptedException, IOException {
    String[] serverResponse;
    ExecutorService requestExecutor = Executors.newFixedThreadPool(1);
    Server testServer = new Server(
            new String[]{"-p", "4043"},
            requestExecutor,
            new ReadRequest(),
            new SendResponse(),
            new Router(),
            logger);
    Thread testServerThread = new Thread(testServer);
    testServerThread.start();
    Thread.sleep(100);
    Socket testSocket = new Socket("localhost", 4043);
    DataOutputStream sendToServer =
            new DataOutputStream(testSocket.getOutputStream());
    BufferedReader readFromServer =
            new BufferedReader(new InputStreamReader(testSocket.getInputStream()));

    sendToServer.writeBytes("GET /TestDirectory/testFile1.txt HTTP/1.1\r\n\r\n");
    sendToServer.flush();

    serverResponse = readFromServer.readLine().split(" ");
    String actualResponse = String.join(" ", serverResponse);
    String expectedResponse = "HTTP/1.1 200 OK";

    testServer.stop();

    assertEquals(actualResponse, expectedResponse);
  }

  @Test
  void serverSendsRedirectToValidPostRequest() throws InterruptedException, IOException {
    ServerUtils serverUtils = new ServerUtils();
    String[] serverResponse;
    ExecutorService requestExecutor = Executors.newFixedThreadPool(1);
    Server testServer = new Server(
            new String[]{"-p", "4045"},
            requestExecutor,
            new ReadRequest(),
            new SendResponse(),
            new Router(),
            logger);
    Thread testServerThread = new Thread(testServer);
    testServerThread.start();
    Thread.sleep(100);
    Socket testSocket = new Socket("localhost", 4045);
    DataOutputStream sendToServer =
            new DataOutputStream(testSocket.getOutputStream());
    BufferedReader readFromServer =
            new BufferedReader(new InputStreamReader(testSocket.getInputStream()));

    sendToServer.writeBytes("POST /resources/form HTTP/1.1\r\n");
    String testContent = "first_name=george-michael&last_name=bluth&location=banana-stand";
    String contentLength = serverUtils.getHttpHeaderContentLength(testContent);
    sendToServer.writeBytes("Content-Length: " + contentLength + "\r\n");
    sendToServer.writeBytes("\r\n");
    sendToServer.writeBytes(testContent);
    sendToServer.writeBytes("\r\n\r\n");
    sendToServer.flush();

    serverResponse = readFromServer.readLine().split(" ");
    String actualResponse = String.join(" ", serverResponse);
    String expectedResponse = "HTTP/1.1 200 OK";

    testServer.stop();

    assertEquals(expectedResponse, actualResponse);
  }
}