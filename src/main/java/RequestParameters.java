import java.util.ArrayList;
import java.util.Arrays;

public class RequestParameters {
  private final String directoryPath;
  private final String httpVerb;
  private final String requestPath;
  private final String host;
  private final String userAgent;
  private final String[] accept;

  private RequestParameters(RequestBuilder builder) {
    this.directoryPath = builder.directoryPath;
    this.httpVerb = builder.httpVerb;
    this.requestPath = builder.requestPath;
    this.host = builder.host;
    this.userAgent = builder.userAgent;
    this.accept = builder.accept;
  }



  String getDirectoryPath() { return directoryPath; }

  String getHttpVerb() { return httpVerb; }

  String getRequestPath() { return requestPath; }

  String getHost() { return host; }

  String getUserAgent() { return userAgent; }

  String[] getAccept() { return accept; }

  public static class RequestBuilder {
    private final String directoryPath;
    private String httpVerb;
    private String requestPath;
    private String host;
    private String userAgent;
    private String[] accept;

    public RequestBuilder(String directoryPath) {
      this.directoryPath = directoryPath;
    }

    public RequestBuilder setHttpVerb(ArrayList<String> httpMessage) {
      String initialLine = httpMessage.get(0);
      this.httpVerb = initialLine.split(" ")[0].trim();
      return this;
    }

    public RequestBuilder setRequestPath(ArrayList<String> httpMessage) {
      String initialLine = httpMessage.get(0);
      this.requestPath = initialLine.split(" ")[1].trim();
      return this;
    }

    public RequestBuilder setHost(ArrayList<String> httpMessage) {
      String host = null;
      for(String line: httpMessage) {
        String headerField = line.split(" ")[0];
        if(headerField.equals("Host:")) {
          host = line.split(" ")[1].trim();
        }
      }
      this.host = host;
      return this;
    }

    public RequestBuilder setUserAgent(ArrayList<String> httpMessage) {
      String userAgent = null;
      for(String line: httpMessage) {
        String headerField = line.split(" ")[0];
        if(headerField.equals("User-Agent:")) {
          String[] userAgentList = line.split(" ");
          String[] noHeaderFieldList =
                  Arrays.copyOfRange(userAgentList, 1, userAgentList.length);
          userAgent = String.join(" ", noHeaderFieldList).trim();
        }
      }
      this.userAgent = userAgent;
      return this;
    }


    public RequestBuilder setAccept(ArrayList<String> httpMessage) {
      String[] accept = null;
      for(String line: httpMessage) {
        String headerField = line.split(" ")[0];
        if(headerField.equals("Accept:")) {
          String[] AcceptList = line.split(" ");
          accept = Arrays.copyOfRange(AcceptList, 1, AcceptList.length);
        }
      }
      this.accept = accept;
      return this;
    }

    public RequestParameters build(){ return new RequestParameters(this); }
  }
}
