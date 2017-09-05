import org.junit.jupiter.api.Test;

import java.io.IOException;

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
    RequestParameters requestParameters =
            new RequestParameters.RequestBuilder("/")
            .build();
    Routes testRoutes = new Routes();
    testRoutes.add("/", new ControllerDirectory());
    ResponseParameters testResponse = testRoutes.getResponse(requestParameters);
    assertEquals("HTTP/1.1 200 OK", testResponse.getResponseStatus());
  }
}
