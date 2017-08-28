import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Post {

  ResponseParameters post(RequestParameters _requestParams) throws IOException {

    ArrayList<String> response = new ArrayList<>();

    String fullFilePath = System.getProperty("user.dir") + _requestParams.getRequestPath() + "/form-result.html";
    String outputLocation = _requestParams.getRequestPath() + "/form-result.html";
    HashMap<String, String> formData = parseFormData(_requestParams.getBodyContent());
    this.saveFormData(formData, fullFilePath);

    response.add("HTTP/1.1 302 Found\r\n");
    response.add("Location: " + outputLocation + "\r\n");
    response.add("\r\n");

    return new ResponseParameters(response, "text", "\r\n\r\n");
  }


  HashMap<String, String> parseFormData(String bodyContent) throws UnsupportedEncodingException {
    String[] formfields = bodyContent.split("&");
    HashMap<String, String> formData = new HashMap<>();
    for(String field: formfields) {
      String[] fieldKeyValue = field.split("=");
      formData.put(URLDecoder.decode(fieldKeyValue[0], "UTF-8"), URLDecoder.decode(fieldKeyValue[1], "UTF-8"));
    }
    return formData;
  }


  void saveFormData(HashMap<String, String> formData, String storageDestination) throws IOException {
    FileWriter fileWriter = new FileWriter(storageDestination);
    BufferedWriter writer = new BufferedWriter(fileWriter);
    for (String key: formData.keySet()) {
      String value = formData.get(key);
      String htmlEntry = "<h3>" + key + ": " + value + "</h3>";
      writer.write(htmlEntry);
      writer.newLine();
    }
    writer.close();
  }
}
