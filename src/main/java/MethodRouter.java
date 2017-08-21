import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

class MethodRouter {

  ResponseParameters getResponse(RequestParameters requestParams) throws ParseException, IOException {
    String httpMethod = requestParams.getHttpVerb();
    if (httpMethod.equals("GET")) {
      return this.get(requestParams);
    } else {
      return this.error();
    }
  }

  private ResponseParameters get(RequestParameters requestParams) throws ParseException, IOException {
    Get httpGetter = new Get();
    return httpGetter.get(requestParams);
  }

  private ResponseParameters error() throws IOException {
    Send404 send404 = new Send404();
    return send404.get();
  }
}
