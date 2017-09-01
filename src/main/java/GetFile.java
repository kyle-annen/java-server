import java.io.*;
import java.util.ArrayList;

class GetFile {
  ResponseParametersOld get(RequestParameters requestParams, String filePath) throws IOException {
    ServerUtils utils = new ServerUtils();
    String contentType = utils.getFileMimeType(filePath);
    ResponseFactory responseFactory = new ResponseFactory();
    File file = new File(filePath);
    String fileLength = Long.toString(file.length());
    ArrayList<String> responseHeader =
            responseFactory.getFileHeader(contentType, fileLength);

    return new ResponseParametersOld(responseHeader, "file", filePath);
  }
}



