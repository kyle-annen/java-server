import java.util.ArrayList;
import java.io.*;

public class Get {
  private ArrayList<String> response = new ArrayList<String>();
  private String webRootDirectory = "/resources";
  private String headerDate;
  private Boolean pathExists;
  private Boolean isDirectory;
  private String relativePath;
  private String lineEnd = "\r\n";


  ArrayList<String> get(ArrayList<String> httpMessage) throws IOException {
    ArrayList<String> response = new ArrayList<String>();
    ServerUtils utils = new ServerUtils();
    headerDate = utils.getHttpHeaderDate();
    relativePath = httpMessage.get(0).split(" ")[1];
    String webRootRelativePath = "." + webRootDirectory + relativePath;
    File relativeFilePath = new File(webRootRelativePath);
    pathExists = relativeFilePath.exists();
    isDirectory = relativeFilePath.isDirectory();
    String responseDate = "Date: " + utils.getHttpHeaderDate() + "\r\n";

    //The HTTP header building here will be refactored after file serving is added.

    if (pathExists && isDirectory) {
      GetDirectory directory = new GetDirectory();
      String directoryMessageBody = directory.getDirectoryListing(relativePath);
      response.add("HTTP/1.1 200 OK\r\n");
      response.add(responseDate);
      response.add("Content-Length: " + utils.getHttpHeaderContentLength(directoryMessageBody) + "\r\n");
      response.add("Connection: Close\r\n");
      response.add("Content-Type: text/html\r\n");
      response.add("\r\n");
      response.add(directoryMessageBody + "\r\n");
    } else if (pathExists) {
      String fileServingBody = "<h1>File Serving Functionality Coming Soon!";
      response.add("HTTP/1.1 200 OK\r\n");
      response.add(responseDate);
      String contentLength = utils.getHttpHeaderContentLength(fileServingBody);
      response.add("Content-Length: " + contentLength + "\r\n");
      response.add("Content-Type: text/html\r\n");
      response.add("\r\n");
      response.add(fileServingBody + "\r\n");
    } else {
      response.add("HTTP/1.1 404 Not Found\r\n");
      response.add("\r\n");
      response.add("<h1>404: File Not Found</h1>\r\n");

    }
    response.add("\r\n");
    return response;
  }
}


