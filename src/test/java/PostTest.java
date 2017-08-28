import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {
  RequestParameters testRequestParams;

  PostTest() {
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

  @AfterEach
  void deleteTestFile() {
    String fileLocation = System.getProperty("user.dir") + "/resources/form/form-result.html";
    File formResult = new File(fileLocation);
    formResult.delete();
  }

  @Test
  void postClassExists(){
    Post post = new Post();
  }

  @Test
  void postWillReturnAResponse() throws IOException {
    Post post = new Post();
    ResponseParameters responseParams = post.post(testRequestParams);
    assertEquals(responseParams.responseHeader.get(0), "HTTP/1.1 302 Found\r\n");
    assertEquals(responseParams.responseHeader.get(1), "Location: /resources/form/form-result.html\r\n");
  }

  @Test
  void parseFormDataParsesFormData() throws UnsupportedEncodingException {
    Post post = new Post();
    HashMap<String, String> parsedData = post.parseFormData(testRequestParams.getBodyContent());
    assertEquals("kyle", parsedData.get("first_name"));
    assertEquals("annen", parsedData.get("last_name"));
    assertEquals("kannen@gmail.com", parsedData.get("email"));
  }

  @Test
  void saveFormDataSavesFormData() throws IOException {
    String filePath = System.getProperty("user.dir") + "/resources/form/form-result.html";
    Post post = new Post();
    HashMap<String, String> parsedData = post.parseFormData(testRequestParams.getBodyContent());
    post.saveFormData(parsedData, filePath);
    assertEquals(true, new File(filePath).exists());
  }


}