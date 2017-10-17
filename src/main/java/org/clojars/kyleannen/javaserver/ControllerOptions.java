package org.clojars.kyleannen.javaserver;

import java.io.IOException;

public class ControllerOptions implements ControllerInterface {

  public ControllerOptions() {

  }

  @Override
  public ResponseParameters getResponse(RequestParameters requestParameters) throws IOException {

    return new ResponseParameters.ResponseBuilder(200)
            .setDate()
            .setContentType("text/html")
            .setAccessControlAllowOrigin()
            .build();
  }
}
