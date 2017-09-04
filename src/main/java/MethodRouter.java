import java.io.IOException;
import java.text.ParseException;

class MethodRouter {

  ResponseParameters getResponse(RequestParameters requestParams) throws ParseException, IOException {
    String httpMethod = requestParams.getHttpVerb();

    if (httpMethod.equals("GET")) {
      return this.get(requestParams);
    } else if(httpMethod.equals("POST")) {
      return this.post(requestParams);
    } else {
      return this.error();
    }
  }

  private ResponseParameters get(RequestParameters requestParams) throws ParseException, IOException {
    Get get = new Get();
    return get.get(requestParams);
  }

  private ResponseParameters post(RequestParameters requestParams) throws ParseException, IOException {
    Post post = new Post();
    return post.post(requestParams);
  }

  private ResponseParameters error() throws IOException {
    return new FourOhFour().generateFourOhFourResponse();
  }
}
