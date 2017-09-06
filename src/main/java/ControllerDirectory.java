import java.io.*;
import java.util.*;

class ControllerDirectory implements ControllerInterface {
  @Override
  public ResponseParameters getResponse(RequestParameters requestParameters) throws IOException {
    String relativePath = requestParameters.getRequestPath();
    String filePath = requestParameters.getDirectoryPath() + requestParameters.getRequestPath();
    String directoryBody = getDirectoryListing(filePath, relativePath);
    return new ResponseParameters.ResponseBuilder(200)
            .setContentLength(directoryBody)
            .setContentType(directoryBody)
            .setBodyContent(directoryBody)
            .setBodyType(directoryBody)
            .setDate()
            .build();
  }

  String getDirectoryListing(String filePath, String relativePath) {
    ArrayList<String> directoryContents = filesList(filePath);
    ArrayList<String> formattedDirectory = formatDirectoryHtml(directoryContents, relativePath);
    StringBuilder directoryListing = new StringBuilder();
    for(String s : formattedDirectory) {
      directoryListing.append(s);
    }
    return directoryListing.toString();
  }

  ArrayList<String> filesList(String filePath) {
    File fullFilePath = new File(filePath);
    try {
      return new ArrayList<>(Arrays.asList(fullFilePath.list()));
    } catch (NullPointerException e) {
      return new ArrayList<>();
    }
  }

  ArrayList<String> formatDirectoryHtml(ArrayList<String> directoryList, String relativePath) {

    ArrayList<String> directoryResponseMessage = new ArrayList<>();
    directoryResponseMessage.add("<!DOCTYPE html>\n");
    directoryResponseMessage.add("<html>\n");
    directoryResponseMessage.add("<head>\n");
    directoryResponseMessage.add("<meta charset=\"UTF-8\">\n");
    directoryResponseMessage.add("<title>title</title>\n");
    directoryResponseMessage.add("</head>\n");
    directoryResponseMessage.add("<body>\n");
    directoryResponseMessage.add("<h1>" + relativePath + "</h1>\n");
    directoryResponseMessage.add("<ul>\n");
    if(directoryList.size() <= 0) {
      directoryResponseMessage.add("<li>There are no files in this directory</li>\n");
      directoryResponseMessage.add("</ul>\n</body>\n");
    } else {
      for(String item: directoryList) {
        if(item.contains(".html") || item.contains(".txt")
                || item.contains(".png") || item.contains(".pdf")
                || item.contains(".js") || item.contains(".css")
                || item.contains(".jpg") || item.contains(".gif") || item.contains(".jpeg")) {
          String link = relativePath + "/" + item;
          link = link.replace("//", "/");
          directoryResponseMessage.add("<li><a href='" + link + "'>" + item + "</a></li>\n");
        } else if(item.contains(".")) {
          directoryResponseMessage.add("<li>" + item + "</li>\n");
        } else {
          String directoryLink = relativePath + "/" + item;
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
