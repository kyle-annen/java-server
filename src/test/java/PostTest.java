import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {
  RequestParameters testRequestParams;


  PostTest() {
    ArrayList<String> request = new ArrayList<>();
    request.add("POST /resources/form?name=kyle&email=kyleannen@gmail.com HTTP/1.1\r\n");
    String directory = System.getProperty("user.dir");
    testRequestParams = new RequestParameters.RequestBuilder(directory)
            .setRequestPath(request)
            .setHttpVerb(request)
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
    System.out.println(responseParams.responseHeader);
  }


}