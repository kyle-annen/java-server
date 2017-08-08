import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.*;

class PingPongServerTest {


  @Test
  void SendsCorrectResponseToPings() throws IOException {
    String message = "";
    PingPongServer testServer = new PingPongServer(new String[] {"4001"});
    Thread serverThread = new Thread(testServer);
    serverThread.start();
    Boolean responseReceived = false;

    Socket testSocket = new Socket("localhost",4001);
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

    assertEquals("PONG", message);
    serverThread.interrupt();
  }

  @Test
  void SendsCorrectResponseToIncorrectMessage() throws IOException {
    String message = "";
    PingPongServer testServer = new PingPongServer(new String[] {"2323"});
    Thread serverThread2 = new Thread(testServer);
    serverThread2.start();


    Socket testSocket2 = new Socket("localhost",2323);

    DataOutputStream sendToServer =
            new DataOutputStream(testSocket2.getOutputStream());
    sendToServer.writeBytes("HIGEORGE\n");

    BufferedReader readFromServer =
            new BufferedReader(new InputStreamReader(testSocket2.getInputStream()));
    message = readFromServer.readLine().split(" ")[0];
    testSocket2.close();

    assertNotEquals("PONG", message);
    serverThread2.interrupt();
  }


}