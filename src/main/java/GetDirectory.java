import java.io.*;
import java.util.*;

class GetDirectory {
  private String webDirectoryRelativePath;

  GetDirectory(RequestParameters _requestParams) throws IOException {
    webDirectoryRelativePath = _requestParams.httpMessage.get(0).split(" ")[1];

  }

  ResponseParameters get(String filePath) throws IOException {
    String directoryBody = getDirectoryListing(filePath);
    String contentLength = Long.toString(directoryBody.length());
    ResponseFactory responseFactory = new ResponseFactory();
    ArrayList<String> response =
            responseFactory.getFileHeader("text/html",contentLength);
    return new ResponseParameters(response, "text", directoryBody);
  }

  public String getDirectoryListing(String filePath) {
    ArrayList<String> directoryContents = filesList(filePath);
    ArrayList<String> formattedDirectory = formatDirectoryHtml(directoryContents);
    StringBuilder directoryListing = new StringBuilder();
    for(String s : formattedDirectory) {
      directoryListing.append(s);
    }
    return directoryListing.toString();
  }

  public ArrayList<String> filesList(String filePath) {
    File fullFilePath = new File(filePath);
    try {
      return new ArrayList<>(Arrays.asList(fullFilePath.list()));
    } catch (NullPointerException e) {
      return new ArrayList<>();
    }
  }

  public ArrayList<String> formatDirectoryHtml(ArrayList<String> directoryList) {
    ArrayList<String> directoryResponseMessage = new ArrayList<>();
    directoryResponseMessage.add("<!DOCTYPE html>\n");
    directoryResponseMessage.add("<html>\n");
    directoryResponseMessage.add("<head>\n");
    directoryResponseMessage.add("<meta charset=\"UTF-8\">\n");
    directoryResponseMessage.add("<title>title</title>\n");
    directoryResponseMessage.add("</head>\n");
    directoryResponseMessage.add("<body>\n");
    directoryResponseMessage.add("<h1>" + webDirectoryRelativePath + "</h1>\n");
    directoryResponseMessage.add("<ul>\n");
    if(directoryList.size() <= 0) {
      directoryResponseMessage.add("<li>There are no files in this directory</li>\n");
      directoryResponseMessage.add("</ul>\n</body>\n");
    } else {
      for(String item: directoryList) {
        if(item.contains(".html") || item.contains(".txt")
                || item.contains(".png") || item.contains(".pdf")
                || item.contains(".js") || item.contains(".css")) {
          String link = webDirectoryRelativePath + "/" + item;
          directoryResponseMessage.add("<li><a href='" + link + "'>" + item + "</a></li>\n");
        } else if(item.contains(".")) {
          directoryResponseMessage.add("<li>" + item + "</li>\n");
        } else {
          String directoryLink = webDirectoryRelativePath + "/" + item;
          directoryLink = directoryLink.replace("//", "/");
          String directoryHtmlLink = "<li><a href='" + directoryLink + "'>" + item + "</a></li>\n";
          directoryResponseMessage.add(directoryHtmlLink);
        }
      }
      directoryResponseMessage.add("</ul>\n");
      directoryResponseMessage.add("</body>\n");
      directoryResponseMessage.add("</html>\n\r\n\r\n");
    }
    return directoryResponseMessage;
  }
}
