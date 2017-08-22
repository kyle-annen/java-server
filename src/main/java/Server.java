import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
  Logger logger;
  private int portNumber = 3300;
  private Boolean serverRunning = true;
  private MethodRouter httpRouter;
  private String directoryPath = System.getProperty("user.dir");

  Server(String[] args) {
    logger = new Logger();
    portNumber = this.setPortNumber(portNumber, args);
    directoryPath = this.setDirectoryPath(directoryPath, args, logger);
    logger.log("Serving directory: " + directoryPath);
    httpRouter = new MethodRouter();
  }

  public void run() {
    //pass the instantiated logger here
    this.announceServer(portNumber, logger);
    ServerSocket serverSocket;
    ExecutorService requestExecutor = Executors.newFixedThreadPool(3);
    try {
      serverSocket = new ServerSocket(portNumber);

      while(serverRunning) {
        Socket socket = serverSocket.accept();
        RequestHandler requestHandler = new RequestHandler(directoryPath, socket, logger);
        requestExecutor.submit(requestHandler);
      }
      serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
      this.run();
    }
  }

  private void announceServer(int portNumber, Logger logger) {
    String outputMessage = "Server started at: http://localhost:" +
            Integer.toString(portNumber);
    logger.log(outputMessage);
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
