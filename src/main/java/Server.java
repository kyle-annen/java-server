import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.util.ArrayList;

public class Server implements Runnable {
  private int portNumber = 3300;
  private Boolean serverRunning = true;
  private MethodRouter httpRouter;
  private String directoryPath = System.getProperty("user.dir");

  Server(String[] args) {
    Boolean isValidDirectoryPath;
    if (args.length > 0 && args[0].matches("\\d+")) {
      portNumber = Integer.parseInt(args[0]);
    }
    isValidDirectoryPath = args.length > 1 && new File(args[1]).isDirectory();
    if (isValidDirectoryPath) {
      directoryPath = args[1];
    } else {
      System.out.println("No valid directory provided.");
    }
    System.out.println("Serving directory: " + directoryPath);
    httpRouter = new MethodRouter();
  }

  public void run() {
    System.out.println("Server started at: http://localhost:" +
            Integer.toString(portNumber));
    ServerSocket defaultServerSocket;

    try {
      defaultServerSocket = new ServerSocket(portNumber);

      while(serverRunning) {
        Socket defaultSocket = defaultServerSocket.accept();
        InputStreamReader inputStreamReader =
                new InputStreamReader(defaultSocket.getInputStream());
        BufferedReader readFromClient =
                new BufferedReader(inputStreamReader);
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

        RequestParameters requestParams =
                new RequestParameters(httpMessage, directoryPath, defaultSocket);

        ResponseParameters responseParameters = httpRouter.getResponse(requestParams);

        SendResponse sendResponse = new SendResponse();
        sendResponse.send(responseParameters, defaultSocket);

        readFromClient.close();
        inputStreamReader.close();
        defaultSocket.close();
      }
      defaultServerSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  void stop() {
    serverRunning = false;
  }

}
