import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.util.ArrayList;

public class Server {
  private int portNumber = 3300;
  private Boolean serverRunning = true;

  Server(String[] args) {
    if (args.length > 0 && args[0].matches("\\d+")) portNumber = Integer.parseInt(args[0]);
  }

  void run() {
    System.out.println("Server started at: http://localhost:" + Integer.toString(portNumber));
    ServerSocket defaultServerSocket;

    try {
      defaultServerSocket = new ServerSocket(portNumber);

      while(serverRunning) {
        Socket defaultSocket = defaultServerSocket.accept();
        BufferedReader readFromClient =
                new BufferedReader(new InputStreamReader(defaultSocket.getInputStream()));
        DataOutputStream sendToClient =
                new DataOutputStream(defaultSocket.getOutputStream());
        Boolean reading = true;

        ArrayList<String> httpMessage = new ArrayList<String>();
        String line;

        while(reading) {
          line = readFromClient.readLine();
          if (line.equals("")) {
            reading = false;
          } else {
            httpMessage.add(line);
          }
        }

        MethodRouter httpRouter = new MethodRouter(httpMessage);

        ArrayList<String> httpResponse = httpRouter.getResponse();

        for( String responseLine : httpResponse) {
          sendToClient.writeBytes(responseLine);
        }

        sendToClient.flush();
        readFromClient.close();
        sendToClient.close();
        defaultSocket.close();

      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public void stop() {
    serverRunning = false;
  }

}
