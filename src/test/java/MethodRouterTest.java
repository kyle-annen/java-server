import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MethodRouterTest extends TestDirectorySetup {
  private RequestParameters requestParameters;

  MethodRouterTest() {

  }

  @Test
  void MethodRouterCorrectlyRoutesGetRoute() throws IOException, ParseException {
    ArrayList<String> httpMessage = new ArrayList<>();
    httpMessage.add("GET /TestDirectory HTTP/1.1\r\n");
    String directoryPath = System.getProperty("user.dir");
    requestParameters = new RequestParameters.RequestBuilder(directoryPath)
            .setHttpVerb(httpMessage)
            .setRequestPath(httpMessage)
            .build();
    MethodRouter methodRouter = new MethodRouter();
    ResponseParametersOld responseParams = methodRouter.getResponse(requestParameters);

    String actualHttpHeader = responseParams.responseHeader.get(0);
    String expectedHttpHeader = "HTTP/1.1 200 OK\r\n";
    assertEquals(expectedHttpHeader, actualHttpHeader);

  }

  @Test
  void MethodRouterCorrectlyRoutesBadRoute() throws IOException, ParseException {
    ArrayList<String> httpMessage = new ArrayList<>();
    httpMessage.add("GET /TestDirecto/ry HTTP/1.1\r\n");
    String directoryPath = System.getProperty("user.dir");
    RequestParameters invalidPathRequestParams = new RequestParameters.RequestBuilder(directoryPath)
            .setHttpVerb(httpMessage)
            .setRequestPath(httpMessage)
            .build();
    MethodRouter methodRouter = new MethodRouter();
    ResponseParametersOld responseParams =
            methodRouter.getResponse(invalidPathRequestParams);

    String expectedHttpHeader = "HTTP/1.1 404 Not Found\r\n\r\n";
    String actualHttpHeader = responseParams.responseHeader.get(0);
    assertEquals(expectedHttpHeader, actualHttpHeader);
  }

  @Test
  void methodRouterRespondsCorrectlyToBadHttpVerb() throws IOException, ParseException {
    ArrayList<String> httpMessage = new ArrayList<>();
    httpMessage.add("BADVERB /TestDirectory HTTP/1.1\r\n");
    String directoryPath = System.getProperty("user.dir");
    RequestParameters invalidPathRequestParams = new RequestParameters.RequestBuilder(directoryPath)
            .setHttpVerb(httpMessage)
            .setRequestPath(httpMessage)
            .build();
    MethodRouter methodRouter = new MethodRouter();

    ResponseParametersOld responseParams =
            methodRouter.getResponse(invalidPathRequestParams);

    String expectedHttpHeader = "HTTP/1.1 404 Not Found\r\n\r\n";
    String actualHttpHeader = responseParams.responseHeader.get(0);
    assertEquals(expectedHttpHeader, actualHttpHeader);
  }

  @Test
  void methodRouterRespondsCorrectlyToPostHttpVerb() throws IOException, ParseException {
    ArrayList<String> httpMessage = new ArrayList<>();
    httpMessage.add("POST /resources/form HTTP/1.1\r\n");
    String directoryPath = System.getProperty("user.dir");
    httpMessage.add("Body-Content: first_name=george-michael&last_name=bluth\r\n");
    RequestParameters postParams = new RequestParameters.RequestBuilder(directoryPath)
            .setHttpVerb(httpMessage)
            .setRequestPath(httpMessage)
            .setBodyContent(httpMessage)
            .build();
    MethodRouter methodRouter = new MethodRouter();

    ResponseParametersOld responseParams =
            methodRouter.getResponse(postParams);

    String expectedHttpHeader = "HTTP/1.1 302 Found\r\n";
    String actualHttpHeader = responseParams.responseHeader.get(0);
    assertEquals(expectedHttpHeader, actualHttpHeader);
  }
}
