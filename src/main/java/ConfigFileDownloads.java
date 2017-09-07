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
  }

  public Boolean isDownloadable(RequestParameters requestParameters) {
    String requestPath = requestParameters.getRequestPath();
    String[] fileArray = requestPath.split("\\.");
    String fileExtension = fileArray[fileArray.length - 1];
    return !filesToNotAddDownloadParameter.contains(fileExtension);
  }
}
