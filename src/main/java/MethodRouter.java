import java.text.ParseException;
import java.util.ArrayList;

class MethodRouter {
  private ArrayList<String> httpMessage;
  private String httpMethod;

  MethodRouter(ArrayList<String> clientMessage) {
    httpMessage = clientMessage;
    httpMethod = httpMessage.get(0).split(" ")[0];
  }

  ArrayList<String> getResponse() throws ParseException {
    ArrayList<String> response;
    if (httpMethod.equals("GET")) {
      response = get();
    } else {
      response = error();
    }
    return response;
  }

  private ArrayList<String> get() throws ParseException {
    Get httpGetter = new Get();
    return httpGetter.get(httpMessage);
  }

<<<<<<< HEAD
  private ArrayList<String> error() {
     ArrayList<String> unavailableMessage = new ArrayList<String>();
     unavailableMessage.add("HTTP/1.1 404 Not Found\r\n");
     return unavailableMessage;
=======
  private ResponseParameters error(RequestParameters requestParams) throws IOException {
    Send404 send404 = new Send404();
    return send404.get();
>>>>>>> bed5343... finish png requirement
  }
}
