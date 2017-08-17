import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

class OutputText {
  void send(Socket socket, ArrayList<String> message) throws IOException {
    DataOutputStream outputStream =
            new DataOutputStream(socket.getOutputStream());
    for(String line : message) {
      outputStream.writeBytes(line);
    }
    outputStream.writeBytes("\r\n\r\n");
    outputStream.flush();
    outputStream.close();
  }
}
