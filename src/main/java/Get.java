import java.io.*;

class Get {
  ResponseParameters get(RequestParameters requestParams) throws IOException {
    String relativePath = requestParams.getRequestPath();
    String filePath = requestParams.getDirectoryPath() + relativePath;

    File targetFile = new File(filePath);

    Boolean pathExists = targetFile.exists();
    Boolean isDirectory = targetFile.isDirectory();

    if (pathExists && isDirectory) {
      return new GetDirectory(requestParams).get(filePath);
    } else if (pathExists) {
      return new GetFile().get(filePath);
    } else {
      return new FourOhFour().generateFourOhFourResponse();
    }
  }
}


