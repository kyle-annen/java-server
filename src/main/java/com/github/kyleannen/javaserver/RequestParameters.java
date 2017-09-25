package com.github.kyleannen.javaserver;

import java.util.ArrayList;
import java.util.Arrays;

public class RequestParameters {
  public final String directoryPath;
  public final String httpVerb;
  public final String requestPath;
  public final String host;
  public final String userAgent;
  public final String[] accept;
  public final String bodyContent;
  public final String params;

  private RequestParameters(RequestBuilder builder) {
    this.directoryPath = builder.directoryPath;
    this.httpVerb = builder.httpVerb;
    this.requestPath = builder.requestPath;
    this.host = builder.host;
    this.userAgent = builder.userAgent;
    this.accept = builder.accept;
    this.bodyContent = builder.bodyContent;
    this.params = builder.params;
  }

  String getDirectoryPath() { return directoryPath; }

  String getHttpVerb() { return httpVerb; }

  String getRequestPath() { return requestPath; }

  String getHost() { return host; }

  String getUserAgent() { return userAgent; }

  String[] getAccept() { return accept; }

  String getBodyContent() { return bodyContent; }

  String getParams() { return params; }

  public static class RequestBuilder {
    private final String directoryPath;
    private String httpVerb;
    private String requestPath;
    private String host;
    private String userAgent;
    private String[] accept;
    private String bodyContent;
    private String params;

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
      String fullRequestPath = initialLine.split(" ")[1].trim();
      Boolean containsParams = fullRequestPath.contains("?");
      if(containsParams) {
        this.params = fullRequestPath.split("\\?")[1];
        this.requestPath = fullRequestPath.split("\\?")[0];
      } else {
        this.requestPath = fullRequestPath;
        this.params = "";
      }
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


    public RequestBuilder setBodyContent(ArrayList<String> httpMessage) {
      for(String line: httpMessage) {
        String headerLine = line.split(" ")[0];
        if(headerLine.equals("Body-Content:")) {
          this.bodyContent = line.substring(14);
          return this;
        }
      }
      return this;
    }

    public RequestParameters build(){ return new RequestParameters(this); }
  }
}
