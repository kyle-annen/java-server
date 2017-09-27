package org.clojars.kyleannen.javaserver;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;

class ControllerPost implements ControllerInterface {
  @Override
  public ResponseParameters getResponse(RequestParameters requestParams) throws IOException {
    HashMap<String, String> formData = this.parseFormData(requestParams.getBodyContent());
    String formContent = this.formatFormData(formData);
    return new ResponseParameters.ResponseBuilder(200)
            .setDate()
            .setContentLength(formContent)
            .setContentType(formContent)
            .setBodyType(formContent)
            .setBodyContent(formContent)
            .build();
  }

  HashMap<String, String> parseFormData(String bodyContent) throws UnsupportedEncodingException {
    String[] formFields = bodyContent.split("&");
    HashMap<String, String> formData = new HashMap<>();
    for(String field: formFields) {
      String[] fieldKeyValue = field.split("=");
      formData.put(URLDecoder.decode(fieldKeyValue[0], "UTF-8").trim(),
              URLDecoder.decode(fieldKeyValue[1], "UTF-8").trim());
    }
    return formData;
  }

  String formatFormData(HashMap<String, String> formData) throws IOException {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<meta charset=\"UTF-8\">\n" +
            "<title>Title of the document</title>\n" +
            "</head>\n" +
            "\n" +
            "<body>");
    for (String key: formData.keySet()) {
      String value = formData.get(key);
      stringBuilder.append("<h3>" + key + ": " + value + "</h3>");
    }
    stringBuilder.append("</body>\n" + "\n" + "</html>");
    return stringBuilder.toString();
  }
}
