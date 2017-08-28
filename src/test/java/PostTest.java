import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

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


}