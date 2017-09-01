import java.io.IOException;
import java.text.ParseException;

class MethodRouter {

  ResponseParametersOld getResponse(RequestParameters requestParams) throws ParseException, IOException {
    String httpMethod = requestParams.getHttpVerb();
    if (httpMethod.equals("GET")) {
      return this.get(requestParams);
    } else if(httpMethod.equals("POST")) {
      return this.post(requestParams);
    } else {
      return this.error();
    }
  }

  private ResponseParametersOld get(RequestParameters requestParams) throws ParseException, IOException {
    Get get = new Get();
    return get.get(requestParams);
  }

  private ResponseParametersOld post(RequestParameters requestParams) throws ParseException, IOException {
    Post post = new Post();
    return post.post(requestParams);
  }

  private ResponseParametersOld error() throws IOException {
    Send404 send404 = new Send404();
    return send404.get();
  }
}
