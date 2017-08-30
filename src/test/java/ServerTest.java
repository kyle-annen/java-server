import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ServerTest extends TestDirectorySetup {
  @Test
  void serverSendsValidResponseToValidRequest() {
    try {
      String[] serverResponse;
      ExecutorService requestExecutor = Executors.newFixedThreadPool(3);
      Server testServer = new Server(new String[]{"-p", "4040"}, requestExecutor);
      Thread testServerThread = new Thread(testServer);
      testServerThread.start();

      Socket testSocket = new Socket("localhost", 4040);
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
    } catch (IOException e) {
      this.serverSendsValidResponseToValidRequest();
    }

  }

  @Test
  void serverSendsValidResponseToValidRequestForFile() {
    try {
      String[] serverResponse;
      ExecutorService requestExecutor = Executors.newFixedThreadPool(3);
      Server testServer = new Server(new String[]{"-p", "4043"}, requestExecutor);
      Thread testServerThread = new Thread(testServer);
      testServerThread.start();
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
    } catch (IOException e) {
      this.serverSendsValidResponseToValidRequestForFile();
    }
  }

  @Test
  void serverSendsRedirectToValidPostRequest() {
    ServerUtils serverUtils = new ServerUtils();
    try {
      String[] serverResponse;
      ExecutorService requestExecutor = Executors.newFixedThreadPool(3);
      Server testServer = new Server(new String[]{"-p", "4045"}, requestExecutor);
      Thread testServerThread = new Thread(testServer);
      testServerThread.start();
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
      String expectedResponse = "HTTP/1.1 302 Found";

      testServer.stop();

      assertEquals(actualResponse, expectedResponse);
    } catch (IOException e) {
      this.serverSendsValidResponseToValidRequestForFile();
    }
  }
}