import java.io.*;

class Get {
  ResponseParameters get(RequestParameters requestParams) throws IOException {
    String relativePath = requestParams.httpMessage.get(0).split(" ")[1];
    String filePath = requestParams.directoryPath + relativePath;
    File targetFile = new File(filePath);
    Boolean pathExists = targetFile.exists();
    Boolean isDirectory = targetFile.isDirectory();
    Boolean isPng = targetFile.toString().contains(".png");
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
    } else if(isPng || isPdf) {
      GetFile getFile = new GetFile();
      return getFile.get(requestParams, filePath);
    } else {
      Send404 send404 = new Send404(requestParams);
      return send404.get();
    }
  }
}


