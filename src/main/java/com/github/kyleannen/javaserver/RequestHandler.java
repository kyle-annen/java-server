package com.github.kyleannen.javaserver;

import java.io.IOException;
import java.net.Socket;

public class RequestHandler implements Runnable {
  private String directoryPath;
  private Socket socket;
  private LoggerInterface logger;
  private RouterInterface router;
  private SendInterface sendInterface;
  private ReadInterface readInterface;

  RequestHandler(String directoryPath,
                 Socket socket,
                 Logger logger,
                 RouterInterface router,
                 SendInterface sendInterface,
                 ReadInterface readInterface) {
    this.directoryPath = directoryPath;
    this.socket = socket;
    this.logger = logger;
    this.router = router;
    this.sendInterface = sendInterface;
    this.readInterface = readInterface;
  }

  public void run() {
    try {
      RequestParameters requestParams =
              this.readInterface.getRequest(this.socket, this.directoryPath);
      ResponseParameters responseParams =
              this.router.route(requestParams);

      this.sendInterface.send(responseParams, this.socket);
    } catch (IOException e) {
      logger.log(e.toString());
    }
  }
}
