import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Router {
  HashMap<String, Routes> router;

  Router() {
    router = new HashMap<>();
  }

  void addRoute(
          String httpMethod,
          String route,
          ControllerInterface controller) {
    Boolean methodRouteExists = router.keySet().contains(httpMethod);
    Routes routes;
    if(methodRouteExists) { routes = router.get(httpMethod);} else { routes = new Routes();}
    Boolean pathRouteExists = routes.keySet().contains(route);
    if(!pathRouteExists) { routes.add(route, controller); }
    router.put(httpMethod, routes);
  }

  ResponseParameters route(RequestParameters requestParameters) throws IOException {

    String httpMethod = requestParameters.getHttpVerb();
    File file = new File(requestParameters.getDirectoryPath() +
            requestParameters.getRequestPath());
    Boolean isDirectory = file.isDirectory();
    Boolean isFile = file.exists() && !isDirectory;
    Boolean methodExists = router.keySet().contains(httpMethod);
    Boolean routeExists = router
            .get(httpMethod)
            .keySet()
            .contains(requestParameters.getRequestPath());

    if(methodExists && routeExists) {
      Routes routes = router.get(httpMethod);
      return routes.getResponse(requestParameters);
    } else if(isDirectory) {
      ControllerDirectory controllerDirectory = new ControllerDirectory();
      return dynamicRoute(requestParameters, controllerDirectory);
    } else if(isFile) {
      ControllerFile controllerFile = new ControllerFile();
      return dynamicRoute(requestParameters, controllerFile);
    } else {
      return new ControllerFourOhFour().getResponse(requestParameters);
    }
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
