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
      return this.error(requestParams);
    }
  }

  private ResponseParameters get(RequestParameters requestParams) throws ParseException, IOException {
    Get get = new Get();
    return get.getResponse(requestParams);
  }

  private ResponseParameters post(RequestParameters requestParams) throws ParseException, IOException {
    ControllerPost post = new ControllerPost();
    return post.getResponse(requestParams);
  }

  private ResponseParameters error(RequestParameters requestParameters) throws IOException {
    return new ControllerFourOhFour().getResponse(requestParameters);
  }
}
