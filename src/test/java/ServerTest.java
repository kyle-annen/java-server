import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.net.*;

class ServerTest extends TestDirectorySetup {
  @Test
  void serverSendsValidResponseToValidRequest() {
    try {
      String[] serverResponse;
      Server testServer = new Server(new String[]{"4040"});
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
      Server testServer = new Server(new String[]{"4043"});
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
}