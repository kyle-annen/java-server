import javax.naming.ldap.Control;
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
    String route = requestParameters.getRequestPath();
    Boolean isValidRoute = false;
    if(router.keySet().contains(httpMethod)) {
      Routes routes = router.get(httpMethod);
      return routes.getResponse(requestParameters);
    } else {
      return new ControllerFourOhFour().getResponse(requestParameters);
    }
  }

}
