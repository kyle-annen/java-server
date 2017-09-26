package org.clojars.kyleannen.javaserver;

import java.util.HashMap;

public class StatusCodes {
  private HashMap<Integer, String> statusCodes;

  StatusCodes() {
    this.statusCodes = new HashMap<>();
    this.statusCodes.put(100, "100 Continue");
    this.statusCodes.put(101, "101 Switching Protocols");

    this.statusCodes.put(200, "200 OK");
    this.statusCodes.put(201, "201 Created");
    this.statusCodes.put(202, "202 Accepted");
    this.statusCodes.put(203, "203 Non-Authoritative Information");
    this.statusCodes.put(204, "204 No Content");
    this.statusCodes.put(205, "205 Reset Content");

    this.statusCodes.put(300, "300 Multiple Choices");
    this.statusCodes.put(301, "301 Moved Permanently");
    this.statusCodes.put(302, "302 Found");
    this.statusCodes.put(303, "303 See Other");
    this.statusCodes.put(305, "305 Use Proxy");
    this.statusCodes.put(307, "307 Temporary Redirect");

    this.statusCodes.put(400, "400 Bad Request");
    this.statusCodes.put(401, "401 Unauthorized");
    this.statusCodes.put(402, "402 Payment Required");
    this.statusCodes.put(403, "403 Forbidden");
    this.statusCodes.put(404, "404 Not Found");
    this.statusCodes.put(405, "405 Method Not Allowed");
    this.statusCodes.put(406, "406 Not Acceptable");
    this.statusCodes.put(408, "408 Request Timeout");
    this.statusCodes.put(409, "409 Conflict");
    this.statusCodes.put(410, "410 Gone");
    this.statusCodes.put(411, "411 Length Required");
    this.statusCodes.put(415, "415 Unsupported Media Type");
    this.statusCodes.put(417, "417 Expectation Failed");
    this.statusCodes.put(418, "418 I'm a teapot");
    this.statusCodes.put(426, "426 Upgrade Required");
    this.statusCodes.put(451, "451 Unavailable For Legal Reasons");

    this.statusCodes.put(500, "500 Internal Server Error");
    this.statusCodes.put(501, "501 Not Implemented");
    this.statusCodes.put(502, "502 Bad Gateway");
    this.statusCodes.put(503, "503 Service Unavailable");
    this.statusCodes.put(504, "504 Gateway Timeout");
    this.statusCodes.put(505, "505 HTTP Version Not Supported");
  }

  public String get(Integer statusCode) {
    return this.statusCodes.get(statusCode);
  }

}

