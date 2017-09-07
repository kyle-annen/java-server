import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ControllerPostTest {
  RequestParameters testRequestParams;

  ControllerPostTest() {
    ServerUtils serverUtils = new ServerUtils();
    ArrayList<String> request = new ArrayList<>();
    String testContent = "first_name=kyle&last_name=annen&email=kannen%40gmail.com\r\n";
    String testContentLength = serverUtils.getHttpHeaderContentLength(testContent);
    request.add("POST /resources/form HTTP/1.1\r\n");

    request.add("Content-Length: " + testContentLength + "\r\n");
    request.add("\r\n");
    request.add("Body-Content: " + testContent);
    request.add("\r\n");
    String directory = System.getProperty("user.dir");
    testRequestParams = new RequestParameters.RequestBuilder(directory)
            .setRequestPath(request)
            .setHttpVerb(request)
            .setBodyContent(request)
            .build();
  }


  @Test
  void postClassExists(){
    ControllerPost post = new ControllerPost();
  }

  @Test
  void postWillReturnAResponse() throws IOException {
    ControllerPost post = new ControllerPost();
    ResponseParameters responseParams = post.getResponse(testRequestParams);
    String expected = "HTTP/1.1 200 OK\r\n";
    String actual = responseParams.getResponseStatus();
    assertEquals(expected, actual);
  }

  @Test
  void parseFormDataParsesFormData() throws UnsupportedEncodingException {
    ControllerPost post = new ControllerPost();
    HashMap<String, String> parsedData = post.parseFormData(testRequestParams.getBodyContent());
    assertEquals("kyle", parsedData.get("first_name"));
    assertEquals("annen", parsedData.get("last_name"));
    assertEquals("kannen@gmail.com", parsedData.get("email"));
  }

  @Test
  void saveFormDataSavesFormData() throws IOException {
    String filePath = System.getProperty("user.dir") + "/resources/form/form-result.html";
    ControllerPost post = new ControllerPost();
    HashMap<String, String> parsedData = post.parseFormData(testRequestParams.getBodyContent());
    assertEquals(true, new File(filePath).exists());
  }
}