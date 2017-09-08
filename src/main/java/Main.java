import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  public static void main(String[] args) {
    ExecutorService requestExecutor = Executors.newFixedThreadPool(10);
    ReadRequest readRequest = new ReadRequest();
    SendResponse sendResponse = new SendResponse();
    Router router = new Router();
    Logger logger = new Logger();

    Server httpServer = new Server(
            args,
            requestExecutor,
            readRequest,
            sendResponse,
            router,
            logger);

    Runtime.getRuntime().addShutdownHook(new ShutdownHook(httpServer, requestExecutor));

    httpServer.run();
  }
}