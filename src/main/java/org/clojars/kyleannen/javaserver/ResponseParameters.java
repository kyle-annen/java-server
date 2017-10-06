package org.clojars.kyleannen.javaserver;

import java.io.File;
import java.io.IOException;

public class ResponseParameters {
  private final String responseStatus;
  private final String contentDisposition;
  private final String contentType;
  private final String contentLength;
  private final String date;
  private final String bodyType;
  private final String bodyContent;
  private final String connectionClose;
  private final String accessControlAllowOrigin;

  private ResponseParameters(ResponseBuilder builder) {
    this.responseStatus = builder.responseStatus;
    this.contentDisposition = builder.contentDisposition;
    this.contentType = builder.contentType;
    this.contentLength = builder.contentLength;
    this.date = builder.date;
    this.bodyType = builder.bodyType;
    this.bodyContent = builder.bodyContent;
    this.connectionClose = "Connection: close\r\n";
    this.accessControlAllowOrigin = builder.accessControlAllowOrigin;

  }

  public String getResponseStatus() { return responseStatus; }

  public String getContentLength() { return contentLength; }

  public String getContentType() { return contentType; }

  public String getDate() { return date; }

  public String getBodyType() { return  bodyType; }

  public String getBodyContent() { return bodyContent;}

  public String getConnectionClose() { return connectionClose; }

  public String getContentDisposition() { return contentDisposition; }

  public String getAccessControlAllowOrigin() { return accessControlAllowOrigin; }

  public static class ResponseBuilder {
    private final String responseStatus;
    private ServerUtils serverUtils;
    private String contentDisposition;
    private String contentType;
    private String contentLength;
    private String date;
    private String bodyType;
    private String bodyContent;
    private String accessControlAllowOrigin;

    public ResponseBuilder(Integer responseCode) {
      serverUtils = new ServerUtils();
      StatusCodes statusCodes = new StatusCodes();
      String responseStatus = statusCodes.get(responseCode);
      this.responseStatus = "HTTP/1.1 " + responseStatus + "\r\n";
    }

    public ResponseBuilder setContentDisposition(String filepathOrTextContent) {
      ConfigFileDownloads configFileDownloads = new ConfigFileDownloads();
      Boolean isDownloadable = configFileDownloads.isDownloadable(filepathOrTextContent);
      if(isDownloadable) {
        String[] fileArray = filepathOrTextContent.split("/");
        String fileName = fileArray[fileArray.length - 1];
        this.contentDisposition =
                "Content-Disposition: attachment; filename=\"" + fileName + "\"\r\n";
      } else {
        this.contentDisposition = "Content-Disposition: inline;\r\n";
      }
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
      this.contentType = "Content-Type: " + mimeType + "\r\n";
      return this;
    }

    public ResponseBuilder setContentLength(String filePathOrTextContent) {
      Boolean isFilePath = new File(filePathOrTextContent).exists();
      String contentLength;
      if (isFilePath) {
        contentLength = Long.toString(new File(filePathOrTextContent).length() + 2);
      } else {
        contentLength = serverUtils.getHttpHeaderContentLength(filePathOrTextContent);
      }
      this.contentLength = "Content-Length: " + contentLength + "\r\n";
      return this;
    }

    public ResponseBuilder setDate () {
      this.date = "Date: " + serverUtils.getHttpHeaderDate() + "\r\n";
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

    public ResponseBuilder setAccessControlAllowOrigin(String host) {
      this.accessControlAllowOrigin = "Access-Control-Allow-Origin: http://" + host + "\r\n";
      return this;
    }

    public ResponseParameters build() { return new ResponseParameters(this); }
  }
}
