public class ResponseParameters {
  private final String responseStatus;
  private final String contentLength;
  private final String contentType;
  private final String date;
  private final String cookie;

  private ResponseParameters(ResponseBuilder builder) {
    this.responseStatus = builder.responseStatus;
    this.contentLength = builder.contentLength;
    this.contentType = builder.contentType;
    this.date = builder.date;
    this.cookie = builder.cookie;
  }

  String getResponseStatus() { return responseStatus; }

  String getContentLength() { return contentLength; }

  String getContentType() { return contentType; }

  String getDate() { return date; }

  String getCookie() { return cookie; }

  public static class ResponseBuilder {
    private final String responseStatus;
    private String contentLength;
    private String contentType;
    private String date;
    private String cookie;

    public ResponseBuilder(Integer responseCode) {
      StatusCodes statusCodes = new StatusCodes();
      this.responseStatus = statusCodes.get(responseCode);
    }

  }

//  public ResponseParameters build() { return new ResponseParameters(this); }

}
