import com.sun.deploy.util.StringUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.net.*;


public class Server {
    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String [] args) throws Exception {
        Integer portNumber = 3300;

        if(args.length > 0 && args[0].matches("\\d+")) portNumber = Integer.parseInt(args[0]);


        System.out.println("Server Started");
        System.out.println("Listening on http://localhost:" + Integer.toString(portNumber));

        ServerSocket listeningSocket = new ServerSocket(portNumber);

        while (true) {
            Socket pingPongSocket = listeningSocket.accept();

            BufferedReader readFromClient =
                    new BufferedReader(new InputStreamReader(pingPongSocket.getInputStream()));

            DataOutputStream sendToClient =
                    new DataOutputStream(pingPongSocket.getOutputStream());

            String messageFromClient = readFromClient.readLine().split("\\ ")[0];


            System.out.println(messageFromClient);
            System.out.println(messageFromClient.equals("PING"));
            Boolean validPingMessage = messageFromClient.equals("PING");
            if(validPingMessage) {
                sendToClient.writeBytes("PONG \n");

            } else {
                sendToClient.writeBytes("Invalid Message. Valid Message is 'PING'.\n");
            }
            pingPongSocket.close();
        }
    }
}
