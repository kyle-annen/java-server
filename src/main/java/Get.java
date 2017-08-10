import java.text.ParseException;
import java.util.ArrayList;


class Get {
  public ArrayList<String> get(ArrayList<String> httpMessage) throws ParseException {
    String urlRelativePath = httpMessage.get(0).split(" ")[1];
    ArrayList<String> response = new ArrayList<String>();
    String lineEnd = "\r\n";
    ServerUtils utils = new ServerUtils();
    String d = utils.getHttpHeaderDate();

    String responseBody = "Hello world!\r\n";
    String contentLength = utils.getHttpHeaderContentLength(responseBody);

    //refactor to a response class
    if(urlRelativePath.equals("/helloworld")) {
      response.add("HTTP/1.1 200 OK\r\n");
      response.add("Date: " + d + "\r\n");
      response.add("Content-Length: " + contentLength + "\r\n");
      response.add("Connection: Close\r\n");
      response.add("Content-Type: text/plain\r\n");
      response.add(lineEnd);
      response.add(responseBody);
    } else if(urlRelativePath.equals("/")) {
      String rootPathBody = "root path\r\n";
      String rootPathBodyLength = utils.getHttpHeaderContentLength(rootPathBody);
      response.add("HTTP/1.1 200 OK\r\n");
      response.add("Date: " + d + "\r\n");
      response.add("Content-Length: " + rootPathBodyLength + "\r\n");
      response.add("Connection: Close\r\n");
      response.add("Content-Type: text/plain\r\n");
      response.add(lineEnd);
      response.add(rootPathBody);
    } else if (urlRelativePath.equals("/ping")) {
      String pongPathBody = "pong\r\n";

      String rootPathBodyLength = utils.getHttpHeaderContentLength(pongPathBody);
      response.add("HTTP/1.1 200 OK\r\n");
      response.add("Date: " + d + "\r\n");
      response.add("Content-Length: " + rootPathBodyLength + "\r\n");
      response.add("Connection: Close\r\n");
      response.add("Content-Type: text/plain\r\n");
      response.add(lineEnd);
      response.add(pongPathBody);


    } else {
      response.add("HTTP/1.1 404 Not Found\r\n");
      response.add("Connection: Close\r\n");
      response.add("Content-Type: text/plain\r\n");
      response.add("404: Page not found\r\n");
      response.add("This is not the page you are looking for.\r\n");
    }
    response.add("\r\n");

    return response;
  }


}
