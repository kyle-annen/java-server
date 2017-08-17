import java.io.BufferedReader;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class GetHtmlText {


  ResponseParameters get(RequestParameters requestParams, String filePath) throws IOException {
    ServerUtils utils = new ServerUtils();
    ResponseFactory responseFactory = new ResponseFactory();
    String contentLength = Integer.toString(this.getFileContents(filePath).length());
    String contentType = utils.getFileMimeType(filePath);
    ArrayList<String> response =
            responseFactory.getFileHeader(contentType, contentLength);
    return new ResponseParameters(response, "file", filePath);
  }

  String getFileContents(String fullFilePath) {
    StringBuilder contentBuilder = new StringBuilder();
    try {
      BufferedReader in = new BufferedReader(new FileReader(fullFilePath));

      String str;
      while((str = in.readLine()) != null) {
        contentBuilder.append(str);
      }
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return contentBuilder.toString();
  }
}
