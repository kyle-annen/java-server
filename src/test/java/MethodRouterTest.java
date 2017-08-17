import org.junit.jupiter.api.Test;

import java.text.ParseException;
<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.util.*;
>>>>>>> bed5343... finish png requirement

import static org.junit.jupiter.api.Assertions.*;

class MethodRouterTest {
  MethodRouter testRouter;
  private ArrayList<String> testGetRoot = new ArrayList<String>();
  private ArrayList<String> testGetBadRoot = new ArrayList<String>();
  private ArrayList<String> testBadHttpVerb = new ArrayList<String>();


  MethodRouterTest() {
<<<<<<< HEAD
    testGetRoot.add("GET / HTTP/1.1\r\n");
    testGetRoot.add("Host: localhost:2323\r\n");
    testGetRoot.add("User-Agent: curl/7.51.0\r\n");
    testGetRoot.add("Accept: */*\r\n");
    testGetRoot.add("\r\n");
=======
    ArrayList<String> httpMessage = new ArrayList<>();
    httpMessage.add("GET /TestDirectory HTTP/1.1\r\n");
    String directoryPath = System.getProperty("user.dir");
    requestParameters = new RequestParameters(httpMessage, directoryPath);
  }
>>>>>>> bed5343... finish png requirement

    testGetBadRoot.add("GET // HTTP/1.1\r\n\r\n");

    testBadHttpVerb.add("DANCEINTHERAIN /danceitup HTTP/1.1\r\n\r\n");
  }


  @Test
  void getResponseRespondsToCorrectGetRouteWith200OK() throws ParseException {
    testRouter = new MethodRouter(testGetRoot);
    ArrayList<String> testResponse = testRouter.getResponse();
    String actual = testResponse.get(0);
    String expected = "HTTP/1.1 200 OK\r\n";
    assertEquals(expected, actual);
  }

  @Test
  void getResonseRespondsToIncorrectGetRouteWith404NotFound() throws ParseException {
    testRouter = new MethodRouter(testGetBadRoot);
    ArrayList<String> testResponse = testRouter.getResponse();
    String actual = testResponse.get(0);
    String expected = "HTTP/1.1 404 Not Found\r\n";
    assertEquals(actual, expected);
  }

  @Test
  void getResponseRespondsToIncorrectHttpVerbWith404NotFound() throws ParseException {
    testRouter = new MethodRouter(testBadHttpVerb);
    ArrayList<String> testResponse = testRouter.getResponse();
    String actual = testResponse.get(0);
    String expected = "HTTP/1.1 404 Not Found\r\n";
    assertEquals(actual, expected);
  }

}