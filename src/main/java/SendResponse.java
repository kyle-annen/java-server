import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class SendResponse {

  void send(ResponseParameters responseParameters, Socket socket) throws IOException {
    Boolean hasFile = responseParameters.bodyType.equals("file");

    if(hasFile) {
      new OutputFile().send(socket, responseParameters.responseHeader, responseParameters.body);
    } else {
      ArrayList<String> response = responseParameters.responseHeader;
      response.add(responseParameters.body);
      new OutputText().send(socket, responseParameters.responseHeader);
    }
  }
}
