import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class MethodRouterTest extends TestDirectorySetup {
  private RequestParameters requestParameters;

  MethodRouterTest() {
    ArrayList<String> httpMessage = new ArrayList<>();
    httpMessage.add("GET /TestDirectory HTTP/1.1\r\n");
    String directoryPath = System.getProperty("user.dir");
    Socket testSocket = new Socket();
    requestParameters = new RequestParameters(httpMessage, directoryPath, testSocket);
  }

  @Test
  void MethodRouterCorrectlyRoutesGetRoute() throws IOException, ParseException {
    MethodRouter methodRouter = new MethodRouter();
    ResponseParameters responseParams = methodRouter.getResponse(requestParameters);

    String actualHttpHeader = responseParams.responseHeader.get(0);
    String expectedHttpHeader = "HTTP/1.1 200 OK\r\n";
    assertEquals(expectedHttpHeader, actualHttpHeader);

  }

  @Test
  void MethodRouterCorrectlyRoutesBadRoute() throws IOException, ParseException {
    MethodRouter methodRouter = new MethodRouter();
    RequestParameters invalidPathRequestParams = requestParameters;
    String invalidRoute = "GET /thisIsABadRoute HTTP/1.1\r\n";
    invalidPathRequestParams.httpMessage.set(0, invalidRoute);

    ResponseParameters responseParams =
            methodRouter.getResponse(invalidPathRequestParams);

    String expectedHttpHeader = "HTTP/1.1 404 Not Found\r\n\r\n";
    String actualHttpHeader = responseParams.responseHeader.get(0);
    assertEquals(expectedHttpHeader, actualHttpHeader);
  }

  @Test
  void methodRouterRespondsCorrectlyToBadHttpVerb() throws IOException, ParseException {
    MethodRouter methodRouter = new MethodRouter();
    RequestParameters invalidPathRequestParams = requestParameters;
    String invalidRoute = "BADVERB /thisIsABadRoute HTTP/1.1\r\n";
    invalidPathRequestParams.httpMessage.set(0, invalidRoute);

    ResponseParameters responseParams =
            methodRouter.getResponse(invalidPathRequestParams);

    String expectedHttpHeader = "HTTP/1.1 404 Not Found\r\n\r\n";
    String actualHttpHeader = responseParams.responseHeader.get(0);
    assertEquals(expectedHttpHeader, actualHttpHeader);
  }
}
