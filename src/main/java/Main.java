import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  public static void main(String[] args) {
    ExecutorService requestExecutor = Executors.newFixedThreadPool(100);
    Server httpServer = new Server(args, requestExecutor);
    Runtime.getRuntime().addShutdownHook(new ShutdownHook(httpServer, requestExecutor));
    httpServer.run();
  }
}
