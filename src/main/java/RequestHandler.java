import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;

public class RequestHandler implements Runnable {
  private String directoryPath;
  Socket socket;
  private Logger logger;

  RequestHandler(String _directoryPath, Socket _socket, Logger _logger) {
    directoryPath = _directoryPath;
    socket = _socket;
    logger = _logger;
  }

  public void run() {

    try {
      ArrayList<String> httpMessage = new ArrayList<>();
      InputStreamReader inputStreamReader =
              new InputStreamReader(socket.getInputStream());
      BufferedReader bufferedReader =
              new BufferedReader(inputStreamReader);

      httpMessage = this.readHttpMessage(bufferedReader, httpMessage);

      Boolean containsContent = this.containsContent(httpMessage);

      httpMessage = containsContent ?
              this.readContentBody(bufferedReader, httpMessage)
              : httpMessage;

      RequestParameters requestParams =
              new RequestParameters.RequestBuilder(directoryPath)
                      .setHttpVerb(httpMessage)
                      .setRequestPath(httpMessage)
                      .setHost(httpMessage)
                      .setUserAgent(httpMessage)
                      .setAccept(httpMessage)
                      .setSocket(socket)
                      .setBodyContent(httpMessage)
                      .build();

      MethodRouter httpRouter = new MethodRouter();

      ResponseParameters responseParams =
              httpRouter.getResponse(requestParams);

      new SendResponse().send(responseParams, socket);

      this.closeConnections(bufferedReader, inputStreamReader, socket);
    } catch (IOException | ParseException e) {
      logger.log(e.toString());
    }
  }

  private Boolean containsContent(ArrayList<String> httpMessage) {
    for(String line: httpMessage) {
      String headerType = line.split(" ")[0];
      if(headerType.equals("Content-Length:")) {
        return true;
      }
    }
    return false;
  }

  private void closeConnections(
          BufferedReader bufferedReader,
          InputStreamReader inputStreamReader,
          Socket socket) throws IOException {
    bufferedReader.close();
    inputStreamReader.close();
    socket.close();
  }

  private ArrayList<String> readHttpMessage(
          BufferedReader bufferedReader,
          ArrayList<String> httpMessage) throws IOException {
    Boolean reading = true;
    String line;
    while(reading) {
      line = bufferedReader.readLine();
      if(line.equals("")) { reading = false; }
      else {
        httpMessage.add(line); }
    }
    return httpMessage;
  }

  private ArrayList<String> readContentBody(
          BufferedReader bufferedReader,
          ArrayList<String> httpMessage) throws IOException {
    int contentLength = this.getContentLength(httpMessage);
    String content = "";
    int length = 0;
    while(length < contentLength) {
      int value = bufferedReader.read();
      if(value != -1) { content += (char)value; }
      length += 1;
    }
    httpMessage.add("Body-Content: " + content);
    return httpMessage;
  }

  private int getContentLength(ArrayList<String> httpMessage) {
    int contentLength = 0;
    for(String line: httpMessage) {
      String headerLabel = line.split(" ")[0];
      String headerValue = line.split(" ")[1];
      if (headerLabel.equals("Content-Length:")) {
        contentLength = Integer.parseInt(headerValue.trim());
      }
    }
    return contentLength;
  }

}
