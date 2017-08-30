import java.io.*;

class Get {
  ResponseParameters get(RequestParameters requestParams) throws IOException {
    String relativePath = requestParams.getRequestPath();
    String filePath = requestParams.getDirectoryPath() + relativePath;

    File targetFile = new File(filePath);
    filePath = targetFile.toString();

    Boolean pathExists = targetFile.exists();
    Boolean isDirectory = targetFile.isDirectory();
    Boolean isPng = targetFile.toString().contains(".png");
    Boolean isJpg = targetFile.toString().contains(".jpg");
    Boolean isJpeg = targetFile.toString().contains(".jpeg");
    Boolean isGif = targetFile.toString().contains(".gif");
    Boolean isTxtFile = targetFile.toString().contains(".txt");
    Boolean isHtmlFile = targetFile.toString().contains(".html");
    Boolean isCssFile = targetFile.toString().contains(".css");
    Boolean isJsFile = targetFile.toString().contains(".js");
    Boolean isPdf = targetFile.toString().contains(".pdf");

    Boolean isTextFile = isCssFile || isJsFile || isHtmlFile || isTxtFile;

    if (pathExists && isDirectory) {
      GetDirectory directory = new GetDirectory(requestParams);
      return directory.get(filePath);
    } else if (isTextFile) {
      GetHtmlText getHtmlText = new GetHtmlText();
      return getHtmlText.get(requestParams, filePath);
    } else if(isPng || isPdf || isGif || isJpg || isJpeg) {
      GetFile getFile = new GetFile();
      return getFile.get(requestParams, filePath);
    } else {
      Send404 send404 = new Send404();
      return send404.get();
    }
  }
}


