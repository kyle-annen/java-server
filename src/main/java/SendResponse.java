import java.io.*;
import java.net.Socket;

public class SendResponse implements SendInterface {

  @Override
  public void send(ResponseParameters responseParameters, Socket socket) throws IOException {
    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
    DataOutputStream outputStream =
            new DataOutputStream(bufferedOutputStream);
    String httpHeader = buildHeader(responseParameters);

    outputStream.writeBytes(httpHeader);

    if(responseParameters.getBodyType().equals("text")) {
      outputStream.writeBytes(responseParameters.getBodyContent());
    } else if(responseParameters.getBodyType().equals("file")) {
      File file = new File(responseParameters.getBodyContent());
      FileInputStream fileInputStream = new FileInputStream(file);
      BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

      byte[] buffer = new byte[8192];
      int available = -1;
      while((available = bufferedInputStream.read(buffer)) > 0) {
        bufferedOutputStream.write(buffer, 0, available);
        bufferedOutputStream.flush();
      }
      outputStream.writeBytes("\r\n\r\n");
    }
    outputStream.flush();
  }

  String buildHeader(ResponseParameters responseParameters) {
    String lineEnding = "\r\n";
    return responseParameters.getResponseStatus() +
            responseParameters.getDate() +
            responseParameters.getContentDisposition() +
            responseParameters.getContentLength() +
            responseParameters.getContentType() +
            responseParameters.getConnectionClose() +
            lineEnding;
  }
}
