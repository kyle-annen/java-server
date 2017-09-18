import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

public class SendResponseTest {
  Server server;

  class MockLogger implements LoggerInterface {

    @Override
    public void log(String string) {
    }
  }


  SendResponseTest() {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    ReadRequest readRequest = new ReadRequest();
    SendResponse sendResponse = new SendResponse();
    Router router = new Router();
    MockLogger mockLogger = new MockLogger();
    String[] args = new String[]{"-p","4345"};
    server = new Server(args, executorService, readRequest, sendResponse, router, mockLogger);
  }

  class SendResponseMockedBuildHeader extends SendResponse {
    @Override
    public String buildHeader(ResponseParameters responseParameters) {
      return "GET / HTTP/1.1'\r\n\r\n";
    }
  }


  @Test
  void sendResponseSendsResponse() throws IOException, InterruptedException {
    Thread.sleep(100);
    Thread serverThread = new Thread(server);
    serverThread.start();

    Socket socket = new Socket("localhost", 4345);
    ResponseParameters responseParameters = new ResponseParameters.ResponseBuilder(200)
            .setBodyType("text")
            .setBodyContent("")
            .setDate()
            .build();
    SendResponseMockedBuildHeader sendResponseMockedBuildHeader = new SendResponseMockedBuildHeader();
    sendResponseMockedBuildHeader.send(responseParameters, socket);

    ReadRequest readRequest = new ReadRequest();
    RequestParameters requestParameters = readRequest.getRequest(socket, "/");

    String expected = "200";
    String actual = requestParameters.getRequestPath();
    assertEquals(expected, actual);
    server.stop();

  }
}
