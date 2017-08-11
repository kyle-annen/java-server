import java.util.ArrayList;

class ResponseFactory {
  private ServerUtils utils = new ServerUtils();
  private String lineEnd = "\r\n";
  private String terminateMessage = lineEnd + lineEnd;
  private String date = utils.getHttpHeaderDate();

   ArrayList<String> getFileHeader(
          String contentType,
          String contentLength) {
    ArrayList<String> r = new ArrayList<>();
    r.add("HTTP/1.1 200 OK" + lineEnd);
    r.add("Date: " + date + lineEnd);
    r.add("ContentLength: " + contentLength + lineEnd);
    r.add("ContentType: " + contentType + lineEnd);
    r.add(lineEnd);
    return r;
  }
}
