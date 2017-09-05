import java.io.*;

class Get {
  ResponseParameters getResponse(RequestParameters requestParams) throws IOException {
    String relativePath = requestParams.getRequestPath();
    String filePath = requestParams.getDirectoryPath() + relativePath;

    File targetFile = new File(filePath);

    Boolean pathExists = targetFile.exists();
    Boolean isDirectory = targetFile.isDirectory();

    if (pathExists && isDirectory) {
      return new ControllerDirectory().getResponse(requestParams);
    } else if (pathExists) {
      return new ControllerFile().getResponse(requestParams);
    } else {
      return new ControllerFourOhFour().getResponse(requestParams);
    }
  }
}


