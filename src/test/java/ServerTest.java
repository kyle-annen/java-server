import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerTest {
  private Server server;
  private ArrayList<String> serverOutput = new ArrayList<>();

  class MockLogger implements LoggerInterface {
    @Override
    public void log(String string) {
      serverOutput.add(string);
    }

  }

  ServerTest() {
    String[] args = new String[]{"-p", "4350", "-d", "/"};
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    ReadRequest readRequest = new ReadRequest();
    SendResponse sendResponse = new SendResponse();
    Router router = new Router();
    MockLogger mockLogger = new MockLogger();
    server = new Server(args, executorService, readRequest, sendResponse, router, mockLogger);
  }

  @Test
  void serverIsAbleToBeStarted() throws InterruptedException {
    try {
      Thread serverThread = new Thread(server);
      serverThread.start();
      Thread.sleep(100);
    } catch (InterruptedException ignored) {
    }

  }
}
