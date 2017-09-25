package com.github.kyleannen.javaserver;

import com.github.kyleannen.javaserver.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

public class RouterTest {
  private class MockController implements ControllerInterface {
    @Override
    public ResponseParameters getResponse(RequestParameters requestParameters) {
      return new ResponseParameters.ResponseBuilder(200)
              .build();
    }
  }


  @Test
  void RouterCanBeInstantiated() {
    Router router = new Router();
  }

  @Test
  void routerCanAddRouter() {
    Router router = new Router();
    ControllerPost controllerPost = new ControllerPost();
    router.addRoute("POST", "/", controllerPost);
  }

  @Test
  void routerCanRouteRequestParameters() throws IOException {
    Router router = new Router();
    MockController mockController = new MockController();
    router.addRoute("GET", "/", mockController);
    ArrayList<String> httpRequest = new ArrayList<>();
    httpRequest.add("GET / HTTP/1.1");
    RequestParameters requestParameters =
            new RequestParameters.RequestBuilder("/")
            .setHttpVerb(httpRequest)
            .setRequestPath(httpRequest)
            .build();
    ResponseParameters responseParameters = router.route(requestParameters);
    String expectedResponseHeader = "HTTP/1.1 200 OK\r\n";
    String actualResponseHeader = responseParameters.getResponseStatus();
    assertEquals(expectedResponseHeader, actualResponseHeader);
  }

  @Test
  void routerReturns404ForBadRoute() throws IOException {
    Router router = new Router();
    MockController mockController = new MockController();
    router.addRoute("GET", "/", mockController);
    ArrayList<String> httpRequest = new ArrayList<>();
    httpRequest.add("GET /badRoute HTTP/1.1");
    RequestParameters requestParameters =
            new RequestParameters.RequestBuilder("/")
                    .setHttpVerb(httpRequest)
                    .setRequestPath(httpRequest)
                    .build();
    ResponseParameters responseParameters = router.route(requestParameters);
    String expectedResponseHeader = "HTTP/1.1 404 Not Found\r\n";
    String actualResponseHeader = responseParameters.getResponseStatus();
    assertEquals(expectedResponseHeader, actualResponseHeader);
  }

}
