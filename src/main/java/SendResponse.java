import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.*;

public class SendResponse {

  void send(ResponseParameters responseParameters, Socket socket) throws IOException {
    DataOutputStream outputStream =
            new DataOutputStream(socket.getOutputStream());
    String httpHeader = buildHeader(responseParameters);

    outputStream.writeBytes(httpHeader);

    if(responseParameters.getBodyType().equals("text")) {
      outputStream.writeBytes(responseParameters.getBodyContent());
    } else if(responseParameters.getBodyType().equals("file")) {
      Path filePath = Paths.get(responseParameters.getBodyContent());
      Files.copy(filePath, outputStream);
    }
    outputStream.writeBytes("\r\n\r\n");
    outputStream.flush();
    outputStream.close();
  }

  private String buildHeader(ResponseParameters responseParameters) {
    String lineEnding = "\r\n";
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(responseParameters.getResponseStatus());
    stringBuilder.append(lineEnding);
    stringBuilder.append(responseParameters.getDate());
    stringBuilder.append(responseParameters.getContentLength());
    stringBuilder.append(lineEnding);
    stringBuilder.append(responseParameters.getContentType());
    stringBuilder.append(lineEnding);
    stringBuilder.append(responseParameters.getConnectionClose());
    stringBuilder.append(lineEnding);
    stringBuilder.append(lineEnding);
    return stringBuilder.toString();
  }
}
