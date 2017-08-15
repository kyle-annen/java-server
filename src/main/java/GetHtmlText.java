import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class GetHtmlText {
  Boolean isTextFile;
  Boolean isHtmlFile;
  String filePath;

  GetHtmlText(String relativePath) {
    isHtmlFile = relativePath.contains(".html");
    isTextFile = relativePath.contains(".txt");
    filePath = "." + "/resources" + relativePath;
  }

  String getFileContents()  {
    StringBuilder contentBuilder = new StringBuilder();
    try {
      BufferedReader in = new BufferedReader(new FileReader(filePath));
      String str;
      while((str = in.readLine()) != null) {
        contentBuilder.append(str);
      }
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return contentBuilder.toString();
  }
}
