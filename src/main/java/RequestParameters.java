import java.util.ArrayList;
import java.net.Socket;

public class RequestParameters {
  public ArrayList<String> httpMessage;
  public String directoryPath;
  public Socket defaultSocket;

  RequestParameters(
          ArrayList<String> _httpMessage,
          String _directoryPath,
          Socket _defaultSocket){
    this.httpMessage = _httpMessage;
    this.directoryPath = _directoryPath;
    this.defaultSocket = _defaultSocket;
  }
}
