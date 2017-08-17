import java.util.ArrayList;

public class RequestParameters {
  public ArrayList<String> httpMessage;
  public String directoryPath;

  RequestParameters(
          ArrayList<String> _httpMessage,
          String _directoryPath) {
    this.httpMessage = _httpMessage;
    this.directoryPath = _directoryPath;
  }
}
