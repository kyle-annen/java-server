import java.io.*;
import java.net.*;

class PingPongServer implements Runnable{
  private Integer portNumber = 3300;
  private Boolean serverRunning = true;

  PingPongServer(String[] args) {
    if (args.length > 0 && args[0].matches("\\d+")) portNumber = Integer.parseInt(args[0]);
  }

  public void run() {

    System.out.println("Server Started");
    System.out.println("Listening on http://localhost:" + Integer.toString(portNumber));
    ServerSocket listeningSocket = null;
    try {
      listeningSocket = new ServerSocket(portNumber);

      while (serverRunning) {
        Socket pingPongSocket = listeningSocket.accept();
        BufferedReader readFromClient =
                new BufferedReader(new InputStreamReader(pingPongSocket.getInputStream()));
        DataOutputStream sendToClient =
                new DataOutputStream(pingPongSocket.getOutputStream());
        String messageFromClient = readFromClient.readLine().split(" ")[0];
        Boolean validPingMessage = messageFromClient.equals("PING");
        if (validPingMessage) {
          sendToClient.writeBytes("PONG \n");
        } else {
          sendToClient.writeBytes("Invalid Message. Valid Message is 'PING'.\n");
        }
        pingPongSocket.close();
      }
      listeningSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void stop() {
    serverRunning = false;
  }
}
