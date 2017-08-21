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
    Logger logger = new Logger();
    portNumber = this.setPortNumber(portNumber, args);
    directoryPath = this.setDirectoryPath(directoryPath, args, logger);
    logger.log("Serving directory: " + directoryPath);
    httpRouter = new MethodRouter();
  }

  public void run() {
    this.announceServer(portNumber, new Logger());
    ServerSocket serverSocket;

    try {
      serverSocket = new ServerSocket(portNumber);

      while(serverRunning) {
        Socket socket = serverSocket.accept();
        InputStreamReader inputStreamReader =
                new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader =
                new BufferedReader(inputStreamReader);

        ArrayList<String> httpMessage =
                this.readHttpMessage(bufferedReader);

        RequestParameters requestParams =
                new RequestParameters(httpMessage, directoryPath);

        ResponseParameters responseParams =
                httpRouter.getResponse(requestParams);

        new SendResponse().send(responseParams, socket);

        this.closeConnections(bufferedReader, inputStreamReader, socket);
      }
      serverSocket.close();
    } catch (IOException | ParseException e) {
      e.printStackTrace();
      this.run();
    }
  }

  private void announceServer(int portNumber, Logger logger) {
    String outputMessage = "Server started at: http://localhost:" +
            Integer.toString(portNumber);
    logger.log(outputMessage);
  }

  private void closeConnections(
          BufferedReader bufferedReader,
          InputStreamReader inputStreamReader,
          Socket socket) throws IOException {
    bufferedReader.close();
    inputStreamReader.close();
    socket.close();
  }

  private ArrayList<String> readHttpMessage(
          BufferedReader bufferedReader ) throws IOException {
    Boolean reading = true;
    ArrayList<String> httpMessage = new ArrayList<>();
    String line;
    while(reading) {
      line = bufferedReader.readLine();
      if(line.equals("")) { reading = false; }
      else { httpMessage.add(line); }
    }
    return httpMessage;
  }

  private String setDirectoryPath(String directPath, String[] args, Logger logger) {
    Boolean isValidDirectoryPath = args.length > 1 && new File(args[1]).isDirectory();
    if (isValidDirectoryPath) {
      return args[1];
    } else {
      logger.log("No valid directory provided.");
      return directPath;
    }
  }

  private int setPortNumber(int portNum, String[] args) {
    if (args.length > 0 && args[0].matches("\\d+")) {
      return Integer.parseInt(args[0]);
    } else {
      return portNum;
    }
  }

  void stop() {
    serverRunning = false;
  }
}
