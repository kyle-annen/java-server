package org.clojars.kyleannen.javaserver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StatusCodesTest {

  @Test
  void statusCodesReturnFor100LevelStatusCodes() {
    StatusCodes statusCodes = new StatusCodes();

    String expected100 = "100 Continue";
    String actual100 = statusCodes.get(100);
    assertEquals(expected100, actual100);

    String expected101 = "101 Switching Protocols";
    String actual101 = statusCodes.get(101);
    assertEquals(expected101, actual101);
  }

  @Test
  void statusCodesReturnFor200LevelStatusCode() {
    StatusCodes statusCodes = new StatusCodes();

    String expected200 = "200 OK";
    String actual200 = statusCodes.get(200);
    assertEquals(expected200, actual200);

    String expected201 = "201 Created";
    String actual201 = statusCodes.get(201);
    assertEquals(expected201, actual201);

    String expected202 = "202 Accepted";
    String actual202 = statusCodes.get(202);
    assertEquals(expected202, actual202);

    String expected203 = "203 Non-Authoritative Information";
    String actual203 = statusCodes.get(203);
    assertEquals(expected203, actual203);

    String expected204 = "204 No Content";
    String actual204 = statusCodes.get(204);
    assertEquals(expected204, actual204);

    String expected205 = "205 Reset Content";
    String actual205 = statusCodes.get(205);
    assertEquals(expected205, actual205);
  }

  @Test
  void statusCodesReturnFor300LevelStatusCodes() {
    StatusCodes statusCodes = new StatusCodes();

    String expected300 = "300 Multiple Choices";
    String actual300 = statusCodes.get(300);
    assertEquals(expected300, actual300);

    String expected301 = "301 Moved Permanently";
    String actual301 = statusCodes.get(301);
    assertEquals(expected301, actual301);

    String expected302 = "302 Found";
    String actual302 = statusCodes.get(302);
    assertEquals(expected302, actual302);

    String expected303 = "303 See Other";
    String actual303 = statusCodes.get(303);
    assertEquals(expected303, actual303);

    String expected305 = "305 Use Proxy";
    String actual305 = statusCodes.get(305);
    assertEquals(expected305, actual305);

    String expected307 = "307 Temporary Redirect";
    String actual307 = statusCodes.get(307);
    assertEquals(expected307, actual307);
  }

  @Test
  void statusCodesReturnFor400LevelStatusCodes() {
    StatusCodes statusCodes = new StatusCodes();

    String expected400 = "400 Bad Request";
    String actual400 = statusCodes.get(400);
    assertEquals(expected400, actual400);

    String expected401 = "401 Unauthorized";
    String actual401 = statusCodes.get(401);
    assertEquals(expected401, actual401);

    String expected402 = "402 Payment Required";
    String actual402 = statusCodes.get(402);
    assertEquals(expected402, actual402);

    String expected403 = "403 Forbidden";
    String actual403 = statusCodes.get(403);
    assertEquals(expected403, actual403);

    String expected404 = "404 Not Found";
    String actual404 = statusCodes.get(404);
    assertEquals(expected404, actual404);

    String expected405 = "405 Method Not Allowed";
    String actual405 = statusCodes.get(405);
    assertEquals(expected405, actual405);

    String expected406 = "406 Not Acceptable";
    String actual406 = statusCodes.get(406);
    assertEquals(expected406, actual406);

    String expected408 = "408 Request Timeout";
    String actual408 = statusCodes.get(408);
    assertEquals(expected408, actual408);

    String expected409 = "409 Conflict";
    String actual409 = statusCodes.get(409);
    assertEquals(expected409, actual409);

    String expected410 = "410 Gone";
    String actual410 = statusCodes.get(410);
    assertEquals(expected410, actual410);

    String expected411 = "411 Length Required";
    String actual411 = statusCodes.get(411);
    assertEquals(expected411, actual411);

    String expected415 = "415 Unsupported Media Type";
    String actual415 = statusCodes.get(415);
    assertEquals(expected415, actual415);

    String expected417 = "417 Expectation Failed";
    String actual417 = statusCodes.get(417);
    assertEquals(expected417, actual417);

    String expected418 = "418 I'm a teapot";
    String actual418 = statusCodes.get(418);
    assertEquals(expected418, actual418);

    String expected426 = "426 Upgrade Required";
    String actual426 = statusCodes.get(426);
    assertEquals(expected426, actual426);

    String expected451 = "451 Unavailable For Legal Reasons";
    String actual451 = statusCodes.get(451);
    assertEquals(expected451, actual451);
  }


  @Test
  void statusCodesReturnFor500LevelStatusCodes() {
    StatusCodes statusCodes = new StatusCodes();

    String expected500 = "500 Internal Server Error";
    String actual500 = statusCodes.get(500);
    assertEquals(expected500, actual500);

    String expected501 = "501 Not Implemented";
    String actual501 = statusCodes.get(501);
    assertEquals(expected501, actual501);

    String expected502 = "502 Bad Gateway";
    String actual502 = statusCodes.get(502);
    assertEquals(expected502, actual502);

    String expected503 = "503 Service Unavailable";
    String actual503 = statusCodes.get(503);
    assertEquals(expected503, actual503);

    String expected504 = "504 Gateway Timeout";
    String actual504 = statusCodes.get(504);
    assertEquals(expected504, actual504);

    String expected505 = "505 HTTP Version Not Supported";
    String actual505 = statusCodes.get(505);
    assertEquals(expected505, actual505);
  }
}















