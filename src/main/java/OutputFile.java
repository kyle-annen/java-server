import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.*;
import java.util.ArrayList;

class OutputFile {
  void send( Socket socket, ArrayList<String> message, String _filePath) throws IOException {
    DataOutputStream outputStream =
            new DataOutputStream(socket.getOutputStream());
    for(String line : message) {
      outputStream.writeBytes(line);
    }
    Path filePath = Paths.get(_filePath);
    Files.copy(filePath, outputStream);
    outputStream.writeBytes("\r\n\r\n");
    outputStream.flush();
    outputStream.close();
  }
}
