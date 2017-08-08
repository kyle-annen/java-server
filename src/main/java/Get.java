import java.text.ParseException;
import java.util.ArrayList;
import java.util.*;
import java.text.SimpleDateFormat;


class Get {
  public ArrayList<String> get(ArrayList<String> httpMessage) throws ParseException {
    String urlRelativePath = httpMessage.get(0).split(" ")[2];
    ArrayList<String> response = new ArrayList<String>();
    String d = getServerTime();

    String responseBody = "Hello world!";
    Integer responseLength = responseBody.length();

    //refactor to a response class
    //if(urlRelativePath.equals("/")) {
    if(true) {
      response.add("HTTP/1.1 200 OK");
      response.add("Date: " + d);
      response.add("Content-Length: " + Integer.toString(responseLength));
      response.add("Connection: Close");
      response.add("Content-Type: text/html");
      response.add(responseBody);
    } else {
      response.add("HTTP/1.1 404 Not Found");
    }
    return response;
  }

  String getServerTime() {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat(
            "EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    return dateFormat.format(calendar.getTime());
  }
}
