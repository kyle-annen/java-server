import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class RoutesTest {

  private class ControllerMock implements ControllerInterface {
    @Override
    public ResponseParameters getResponse(RequestParameters requestParameters) {
      return new ResponseParameters.ResponseBuilder(200)
              .setDate()
              .build();
    }
  }

  @Test
  void routeCanBeInitialized() {
    try {
      Routes testRoutes = new Routes();
      assert(true);
    } catch (Exception e){
      assert(false);
    }
  }

  @Test
  void routeCanBeAddedToRoutes() {
    Routes testRoutes = new Routes();
    try {
      testRoutes.add("/", new ControllerDirectory());
      assert(true);
    } catch(Exception e) {
      assert(false);
    }
  }

  @Test
  void routesGetResponseReturnsResponse() throws IOException {
    ArrayList<String> httpRequest = new ArrayList<>();
    httpRequest.add("GET / HTTP/1.1");
    RequestParameters requestParameters =
            new RequestParameters.RequestBuilder("/")
                    .setHttpVerb(httpRequest)
                    .setRequestPath(httpRequest)
                    .build();
    Routes testRoutes = new Routes();
    testRoutes.add("/", new ControllerDirectory());
    ResponseParameters testResponse = testRoutes.getResponse(requestParameters);
    assertEquals("HTTP/1.1 200 OK\r\n", testResponse.getResponseStatus());
  }

  @Test
  void routesGetReturnsImplementationOfRouteInterface() {
    Routes testRoutes = new Routes();
    testRoutes.add("/", new ControllerFourOhFour());

    ControllerInterface actualInterface = testRoutes.get("/");
    ControllerInterface expectedInterface = new ControllerFourOhFour();

    assertEquals(expectedInterface.getClass(), actualInterface.getClass());
  }

  @Test
  void returnsFourOhFourResponseOnBadRoute() throws IOException {
    ArrayList<String> httpMessage = new ArrayList<>();
    httpMessage.add("GET /bad/test/route/not/gonna/work HTTP/1.1\r\n\r\n");
    RequestParameters requestParameters = new RequestParameters.RequestBuilder("/")
            .setHost(httpMessage)
            .setUserAgent(httpMessage)
            .setBodyContent(httpMessage)
            .setRequestPath(httpMessage)
            .setHttpVerb(httpMessage)
            .setAccept(httpMessage)
            .build();
    Routes testRoutes = new Routes();
    testRoutes.add("/", new ControllerFourOhFour());

    ResponseParameters responseParameters = testRoutes.getResponse(requestParameters);
    String actual = responseParameters.getResponseStatus();
    String expected = "HTTP/1.1 404 Not Found\r\n";
    assertEquals(expected, actual);
  }
}
