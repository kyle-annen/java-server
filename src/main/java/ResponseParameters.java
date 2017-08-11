import java.util.ArrayList;

public class ResponseParameters {
  public ArrayList<String> responseHeader;
  public String bodyType;
  public String body;

  ResponseParameters( ArrayList<String> _responseHeader, String _bodyType, String _body) {
    responseHeader = _responseHeader;
    bodyType = _bodyType;
    body = _body;
  }
}
