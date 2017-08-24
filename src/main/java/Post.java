import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Post {

  ResponseParameters post(RequestParameters _requestParams) throws IOException {

    ArrayList<String> response = new ArrayList<>();

    Boolean containsParameters = _requestParams.getRequestPath().contains("?");


    System.out.println(_requestParams.getRequestPath());
    String storageDestination = _requestParams.getRequestPath().split("\\?")[0];
    String fields = _requestParams.getRequestPath().split("\\?")[1];
    String[] fieldsArray = fields.split("&");

    for (String field: fieldsArray) {
      System.out.println(field);
    }



    List<String> listToStore = Arrays.asList(fieldsArray);
    Path storage = Paths.get(_requestParams.getDirectoryPath() + storageDestination + "/formresult.html");
    String redirect = storageDestination + "/formresult.html";


    PrintWriter writer = new PrintWriter(storage.toString(), "UTF-8");
    for (String line : listToStore) {
      writer.println(line);
    }
    writer.close();

    response.add("HTTP/1.1 200 OK\r\n");
    response.add("Refresh: 0; url=" + redirect + "\r\n\r\n");

    return new ResponseParameters(response, "text", "\r\n\r\n");
  }

}
