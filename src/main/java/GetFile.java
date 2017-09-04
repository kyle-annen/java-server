import java.io.*;
import java.util.ArrayList;

class GetFile {
  ResponseParameters get(String filePathOrTextContent) throws IOException {
    ResponseParameters responseParameters = new ResponseParameters.ResponseBuilder(200)
            .setDate()
            .setContentLength(filePathOrTextContent)
            .setContentType(filePathOrTextContent)
            .setBodyType(filePathOrTextContent)
            .setBodyContent(filePathOrTextContent)
            .build();
    return responseParameters;
  }
}



