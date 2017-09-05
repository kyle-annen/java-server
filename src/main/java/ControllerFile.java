import java.io.*;

class ControllerFile implements ControllerInterface {
  @Override
  public ResponseParameters getResponse(RequestParameters requestParameters) throws IOException {
    String filePath = System.getProperty("user.dir") + requestParameters.getRequestPath();
    return new ResponseParameters.ResponseBuilder(200)
            .setDate()
            .setContentLength(filePath)
            .setContentType(filePath)
            .setBodyType(filePath)
            .setBodyContent(filePath)
            .build();
  }
}



