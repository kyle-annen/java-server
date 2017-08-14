import java.io.*;
import java.util.*;

class GetDirectory {
  private String serverRelativePath = new java.io.File(".").getCanonicalPath();
  private String defaultWebDirectory = "/resources";
  private String currentDirectoryPath;
  private String webDirectoryRelativePath;

  GetDirectory() throws IOException {

  }

  String getDirectoryListing(String relativePath) {
    ArrayList<String> directoryContents = filesList(relativePath);
    ArrayList<String> formattedDirectory = formatDirectoryHtml(directoryContents);
    StringBuilder directoryListing = new StringBuilder();
    for(String s : formattedDirectory) {
      directoryListing.append(s);
    }
    return directoryListing.toString();
  }

  private ArrayList<String> filesList(String relativePath) {
    currentDirectoryPath = serverRelativePath + defaultWebDirectory + relativePath;
    System.out.println(currentDirectoryPath);
    webDirectoryRelativePath = relativePath;
    File fullFilePath = new File(currentDirectoryPath);
    try {
      return new ArrayList<String>(Arrays.asList(fullFilePath.list()));
    } catch (NullPointerException e) {
      return new ArrayList<String>();
    }
  }

  private ArrayList<String> formatDirectoryHtml(ArrayList<String> directoryList) {
    ArrayList<String> directoryResponseMessage = new ArrayList<String>();
    directoryResponseMessage.add("<h1>" + webDirectoryRelativePath + "</h1>");
    directoryResponseMessage.add("<ul>");
    if(directoryList.size() <= 0) {
      directoryResponseMessage.add("<li>There are no files in this directory</li>");
    } else {
      for(String item: directoryList) {
        if(item.contains(".")) {
          directoryResponseMessage.add("<li>" + item + "</li>");
        } else {
          String directoryLink = webDirectoryRelativePath + "/" + item;
          if(directoryLink.contains("//")) {directoryLink = directoryLink.substring(1);}
          String directoryHtmlLink = "<li><a href='" + directoryLink + "'>" + item + "</a></li>";
          directoryResponseMessage.add(directoryHtmlLink);
        }
      }
      directoryResponseMessage.add("</ul>\r\n");
    }
    return directoryResponseMessage;
  }
}
