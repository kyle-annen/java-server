package com.github.kyleannen.javaserver;

import java.io.IOException;

public interface RouterInterface {
  ResponseParameters route(RequestParameters requestParameters) throws IOException;
}
