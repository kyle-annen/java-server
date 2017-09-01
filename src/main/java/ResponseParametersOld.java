import java.util.ArrayList;

public class ResponseParametersOld {
  public ArrayList<String> responseHeader;
  public String bodyType;
  public String body;

  ResponseParametersOld(ArrayList<String> _responseHeader, String _bodyType, String _body) {
    responseHeader = _responseHeader;
    bodyType = _bodyType;
    body = _body;
  }
}
