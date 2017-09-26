package com.github.kyleannen.javaserver;
import java.io.File;
import java.util.ArrayList;

class ConfigFileDownloads {
  private ArrayList<String> filesToNotAddDownloadParameter;

  ConfigFileDownloads() {
    filesToNotAddDownloadParameter = new ArrayList<>();
    this.initialize(this.filesToNotAddDownloadParameter);
  }

  private void initialize(ArrayList<String> fileList) {
    fileList.add("jpg");
    fileList.add("jpeg");
    fileList.add("html");
    fileList.add("png");
    fileList.add("pdf");
  }

  public Boolean isDownloadable(String filePath) {
    String[] fileArray = filePath.split("\\.");
    String fileExtension = fileArray[fileArray.length - 1];
    Boolean isDownloadable = !this.filesToNotAddDownloadParameter.contains(fileExtension);
    File file = new File(filePath);
    Boolean isDirectory =  file.isDirectory();
    return isDownloadable && !isDirectory;
  }
}
