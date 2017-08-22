import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

class Send404 {
  ResponseParameters get() throws IOException {
    ArrayList<String> response = new ArrayList<>();
    response.add("HTTP/1.1 404 Not Found\r\n\r\n");
    return new ResponseParameters(response, "text", "<h1>404: File Not Found</h1>");
  }
}