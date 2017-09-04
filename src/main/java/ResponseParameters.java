import javax.xml.ws.Response;
import java.io.File;
import java.io.IOException;

public class ResponseParameters {
  private final String responseStatus;
  private final String contentLength;
  private final String contentType;
  private final String date;
  private final String bodyType;
  private final String bodyContent;
  private final String connectionClose;

  private ResponseParameters(ResponseBuilder builder) {
    this.responseStatus = builder.responseStatus;
    this.contentLength = builder.contentLength;
    this.contentType = builder.contentType;
    this.date = builder.date;
    this.bodyType = builder.bodyType;
    this.bodyContent = builder.bodyContent;
    this.connectionClose = "Connection: close";
  }

  String getResponseStatus() { return responseStatus; }

  String getContentLength() { return contentLength; }

  String getContentType() { return contentType; }

  String getDate() { return date; }

  String getBodyType() { return  bodyType; }

  String getBodyContent() { return bodyContent;}

  String getConnectionClose() { return connectionClose; }

  public static class ResponseBuilder {
    private final String responseStatus;
    private ServerUtils serverUtils;
    private String contentLength;
    private String contentType;
    private String date;
    private String bodyType;
    private String bodyContent;

    public ResponseBuilder(Integer responseCode) {
      serverUtils = new ServerUtils();
      StatusCodes statusCodes = new StatusCodes();
      String responseStatus = statusCodes.get(responseCode);
      this.responseStatus = "HTTP/1.1 " + responseStatus;
    }

    public ResponseBuilder setContentLength(String filePathOrTextContent) {
      Boolean isFilePath = new File(filePathOrTextContent).exists();
      String contentLength;
      if (isFilePath) {
        contentLength = Long.toString(new File(filePathOrTextContent).length());
      } else {
        contentLength = serverUtils.getHttpHeaderContentLength(filePathOrTextContent);
      }
      this.contentLength = "Content-Length: " + contentLength;
      return this;
    }

    public ResponseBuilder setContentType(String filePathOrTextContent) throws IOException {
      Boolean isFilePath = new File(filePathOrTextContent).exists();
      String mimeType;
      if (isFilePath) {
        mimeType = serverUtils.getFileMimeType(filePathOrTextContent);
      } else {
        mimeType = "text/html";
      }
      this.contentType = "Content-Type: " + mimeType;
      return this;
    }

    public ResponseBuilder setDate () {
      this.date = "Date: " + serverUtils.getHttpHeaderDate();
      return this;
    }

    public ResponseBuilder setBodyType(String filePathOrTextContent) {
      Boolean isFilePath = new File(filePathOrTextContent).exists();
      String bodyType;
      if (isFilePath) { bodyType = "file"; } else { bodyType = "text"; }
      this.bodyType = bodyType;
      return this;
    }

    public ResponseBuilder setBodyContent(String filePathOrTextContent) {
      this.bodyContent = filePathOrTextContent;
      return this;
    }

    public ResponseParameters build() { return new ResponseParameters(this); }
  }
}
