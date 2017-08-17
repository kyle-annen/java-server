import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

class MethodRouter {

  ResponseParameters getResponse(RequestParameters requestParams) throws ParseException, IOException {
    ArrayList <String> httpMessage = requestParams.httpMessage;
    String httpMethod = httpMessage.get(0).split(" ")[0];

    if (httpMethod.equals("GET")) {
      return this.get(requestParams);
    } else {
      return this.error(requestParams);
    }
  }

  private ResponseParameters get(RequestParameters requestParams) throws ParseException, IOException {

    Get httpGetter = new Get();
    return httpGetter.get(requestParams);
  }

  private ResponseParameters error(RequestParameters requestParams) throws IOException {
    Send404 send404 = new Send404();
    return send404.get();
  }
}
