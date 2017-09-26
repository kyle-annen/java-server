package org.clojars.kyleannen.javaserver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Router implements RouterInterface {
  private HashMap<String, Routes> router = new HashMap<>();
  private Boolean disableDirectoryRouting = false;
  private Boolean disableFileRouting = false;

  public void addRoute(
          String httpMethod,
          String route,
          ControllerInterface controller) {
    Boolean methodRouteExists = router.keySet().contains(httpMethod);
    Routes routes;
    if(methodRouteExists) { routes = router.get(httpMethod);} else { routes = new Routes();}
    Boolean pathRouteExists = routes.getRoutePaths().contains(route);
    if(!pathRouteExists) { routes.add(route, controller); }
    router.put(httpMethod, routes);
  }

  Routes getRoutes(String httpMethod) {
    return router.get(httpMethod);
  }

  public void disableDirectoryRouting() { this.disableDirectoryRouting = true; }

  public void disableFileRouting() { this.disableFileRouting = true; }


  @Override
  public ResponseParameters route(RequestParameters requestParameters) throws IOException {
    String httpMethod = requestParameters.getHttpVerb();
    File file = new File(requestParameters.getDirectoryPath() +
            requestParameters.getRequestPath());
    Boolean isDirectory = file.isDirectory();
    Boolean isFile = file.exists() && !isDirectory;
    Boolean methodExists = router.keySet().contains(httpMethod);
    Boolean routeExists = router
            .get(httpMethod)
            .getRoutePaths()
            .contains(requestParameters.getRequestPath());
    if(methodExists && routeExists) {
      return router.get(httpMethod).getResponse(requestParameters);
    }
    if(isDirectory && !this.disableDirectoryRouting) {
      return  dynamicRoute(requestParameters, new ControllerDirectory());
    }
    if(isFile && !this.disableFileRouting) {
      return dynamicRoute(requestParameters, new ControllerFile());
    }
    return new ControllerFourOhFour().getResponse(requestParameters);
  }

  private ResponseParameters dynamicRoute(
          RequestParameters requestParameters,
          ControllerInterface controllerInterface) throws IOException {
    Routes dynamicRoutes = new Routes();
    String route = requestParameters.getRequestPath();
    dynamicRoutes.add(route, controllerInterface);
    return dynamicRoutes.getResponse(requestParameters);
  }
}
