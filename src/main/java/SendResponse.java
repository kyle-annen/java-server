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

      byte[] buffer = new byte[1024*128];
      int available = -1;
      while((available = bufferedInputStream.read(buffer)) > 0) {
        bufferedOutputStream.write(buffer, 0, available);
      }
    }
    outputStream.flush();
    outputStream.close();
  }

  private String buildHeader(ResponseParameters responseParameters) {
    String lineEnding = "\r\n";
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(responseParameters.getResponseStatus());
    stringBuilder.append(responseParameters.getDate());
    stringBuilder.append(responseParameters.getContentDisposition());
    stringBuilder.append(responseParameters.getContentLength());
    stringBuilder.append(responseParameters.getContentType());
    stringBuilder.append(responseParameters.getConnectionClose());
    stringBuilder.append(lineEnding);
    stringBuilder.append(lineEnding);
    return stringBuilder.toString();
  }
}
