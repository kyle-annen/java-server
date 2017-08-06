import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.*;

class PingPongServerTest {

    @Test
    void SendsCorrectResponseToPing() throws IOException {
        String message = "";
        PingPongServer testServer = new PingPongServer(new String[] {"3000"});
        Thread serverThread = new Thread(testServer);
        serverThread.start();
        Boolean responseReceived = false;

        while(!responseReceived) {

            Socket testSocket = new Socket("localhost",3000);

            DataOutputStream sendToServer =
                    new DataOutputStream(testSocket.getOutputStream());
            sendToServer.writeBytes("PING\n");

            BufferedReader readFromServer =
                    new BufferedReader(new InputStreamReader(testSocket.getInputStream()));
            message = readFromServer.readLine().split(" ")[0];
            responseReceived = message.equals("PONG");
        }

        serverThread.interrupt();
        assertEquals("PONG", message);
    }
}