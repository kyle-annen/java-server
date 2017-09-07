import java.io.IOException;
import java.net.Socket;

public class RequestHandler implements Runnable {
  private String directoryPath;
  private Socket socket;
  private Logger logger;
  private Router router;
  private SendInterface sendInterface;
  private ReadInterface readInterface;

  RequestHandler(String directoryPath,
                 Socket socket,
                 Logger logger,
                 Router router,
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
      socket.close();
    } catch (IOException e) {
      logger.log(e.toString());
    }
  }
}
