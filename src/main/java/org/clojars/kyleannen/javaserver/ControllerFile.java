package org.clojars.kyleannen.javaserver;

import java.io.*;

class ControllerFile implements ControllerInterface {
  @Override
  public ResponseParameters getResponse(RequestParameters requestParameters) throws IOException {
    String filePath = requestParameters.getDirectoryPath() +
            requestParameters.getRequestPath();
    return new ResponseParameters.ResponseBuilder(200)
            .setDate()
            .setContentDisposition(filePath)
            .setContentLength(filePath)
            .setContentType(filePath)
            .setBodyType(filePath)
            .setBodyContent(filePath)
            .build();
  }
}



