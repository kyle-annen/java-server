package com.github.kyleannen.javaserver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class Routes {
  private HashMap<String, ControllerInterface> routes;

  Routes() { this.routes = new HashMap<>(); }

  public void add(String path, ControllerInterface controller) { this.routes.put(path, controller); }

  public ControllerInterface get(String path) { return this.routes.get(path); }

  Set<String> getRoutePaths() { return this.routes.keySet(); }

  Boolean routeExists(String route) { return this.routes.keySet().contains(route); }

  public ResponseParameters getResponse(RequestParameters requestParameters) throws IOException {
    String path = requestParameters.getRequestPath();
    Boolean routeExists = this.routes.keySet().contains(path);
    if (routeExists) {
      return this.routes.get(path).getResponse(requestParameters);
    } else {
      return new ControllerFourOhFour().getResponse(requestParameters);
    }
  }
}

