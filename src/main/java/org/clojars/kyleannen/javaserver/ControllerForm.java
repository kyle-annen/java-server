package org.clojars.kyleannen.javaserver;

import java.io.IOException;

public class ControllerForm implements ControllerInterface {

  @Override
  public ResponseParameters getResponse(RequestParameters requestParameters) throws IOException {
    String indexFormPath = requestParameters.getDirectoryPath() + "/resources/form/index.html";

    return new ResponseParameters.ResponseBuilder(200)
            .setDate()
            .setContentDisposition(indexFormPath)
            .setContentDisposition(indexFormPath)
            .setContentType(indexFormPath)
            .setBodyType(indexFormPath)
            .setBodyContent(indexFormPath)
            .build();
  }
}
