import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

class ReadRequest implements ReadInterface {
  @Override
  public RequestParameters getRequest(Socket socket, String directoryPath) throws IOException {
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

    return new RequestParameters.RequestBuilder(directoryPath)
            .setHttpVerb(httpMessage)
            .setRequestPath(httpMessage)
            .setHost(httpMessage)
            .setUserAgent(httpMessage)
            .setAccept(httpMessage)
            .setBodyContent(httpMessage)
            .build();
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

  private Boolean containsContent(ArrayList<String> httpMessage) {
    for(String line: httpMessage) {
      String headerType = line.split(" ")[0];
      if(headerType.equals("Content-Length:")) {
        return true;
      }
    }
    return false;
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
}
