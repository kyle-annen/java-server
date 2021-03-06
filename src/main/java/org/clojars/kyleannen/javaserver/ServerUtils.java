package org.clojars.kyleannen.javaserver;

import java.io.IOException;
import java.net.URLConnection;
import java.util.*;
import java.text.SimpleDateFormat;

class ServerUtils {
   String getHttpHeaderDate() {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat(
            "EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    return dateFormat.format(calendar.getTime());
  }

  String getHttpHeaderContentLength(String messageBody) {
     return Integer.toString(messageBody.getBytes().length);
  }

  String getFileMimeType(String filePath) throws IOException {
     if(filePath.contains(".css")) {
       return "text/css";
     } else if(filePath.contains(".json")) {
       return "application/javascript";
     } else {
       return URLConnection.guessContentTypeFromName(filePath);
     }
  }
}
