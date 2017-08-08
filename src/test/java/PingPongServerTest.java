import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.*;

class PingPongServerTest {


  @Test
  void SendsCorrectResponseToPings() throws IOException {
    String message = "";
    PingPongServer testServer = new PingPongServer(new String[] {"3000"});
    Thread serverThread = new Thread(testServer);
    serverThread.start();
    Boolean responseReceived = false;

    Socket testSocket = new Socket("localhost",3000);
    DataOutputStream sendToServer =
            new DataOutputStream(testSocket.getOutputStream());
    sendToServer.writeBytes("PING\n");

    BufferedReader readFromServer =
            new BufferedReader(new InputStreamReader(testSocket.getInputStream()));
    message = readFromServer.readLine().split(" ")[0];
    responseReceived = message.equals("PONG");
    sendToServer.flush();
    sendToServer.close();
    readFromServer.close();
    testSocket.close();
    testServer.stop();

    assertEquals("PONG", message);
    serverThread.interrupt();
  }

  @Test
  void SendsCorrectResponseToIncorrectMessage() throws IOException {
    String message = "";
    PingPongServer testServer2 = new PingPongServer(new String[] {"2323"});
    Thread serverThread2 = new Thread(testServer2);
    serverThread2.start();


    Socket testSocket2 = new Socket("localhost",2323);

    DataOutputStream sendToServer =
            new DataOutputStream(testSocket2.getOutputStream());
    sendToServer.writeBytes("HIGEORGE\n");

    BufferedReader readFromServer =
            new BufferedReader(new InputStreamReader(testSocket2.getInputStream()));
    message = readFromServer.readLine().split(" ")[0];
    testSocket2.close();
    testServer2.stop();
    assertNotEquals("PONG", message);
    serverThread2.interrupt();
  }


}