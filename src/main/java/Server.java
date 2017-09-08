import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;

public class Server implements Runnable{
  private Logger logger;
  private int portNumber = 3300;
  private Boolean serverRunning = true;
  private String directoryPath = System.getProperty("user.dir");
  private ExecutorService requestExecutor;
  private Router router;
  private ReadInterface readInterface;
  private SendInterface sendInterface;

  Server(
          String[] args,
          ExecutorService requestExecutor,
          ReadInterface readInterface,
          SendInterface sendInterface,
          Router router,
          LoggerInterface logger) {
    this.logger = new Logger();
    portNumber = this.setPortNumber(portNumber, args);
    directoryPath = this.setDirectoryPath(directoryPath, args, logger);
    logger.log("Serving directory: " + directoryPath);

    this.requestExecutor = requestExecutor;
    this.router = router;
    this.readInterface = readInterface;
    this.sendInterface = sendInterface;

    new ConfigRoutes(this.router);
  }

  @Override
  public void run() {
    this.announceServer(portNumber, logger);
    ServerSocket serverSocket;
    try {
      serverSocket = new ServerSocket(portNumber);
      while(serverRunning) {
        Socket socket = serverSocket.accept();
        RequestHandler requestHandler =
                new RequestHandler(
                        this.directoryPath, socket, this.logger, router,
                        this.sendInterface, this.readInterface);
        requestExecutor.submit(requestHandler);
      }
      serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void announceServer(int portNumber, Logger logger) {
    String outputMessage = "Server started at: http://localhost:" +
            Integer.toString(portNumber);
    logger.log(outputMessage);
  }

  private String setDirectoryPath(String directPath, String[] args, LoggerInterface logger) {
    for(int i = 0; i < args.length; i++) {
      if(args[i].equals("-d") && new File(args[i + 1]).isDirectory()) {
        return args[i + 1];
      }
    }
    logger.log("No valid directory path provided.");
    return directPath;
  }

  private int setPortNumber(int portNum, String[] args) {
    for (int i = 0; i < args.length; i++ ) {
      if(args[i].equals("-p") && args[i + 1].matches("\\d+")) {
        return Integer.parseInt(args[i + 1]);
      }
    }
    return portNum;
  }

  void stop() {
    serverRunning = false;
  }
}
