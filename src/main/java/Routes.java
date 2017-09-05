import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class Routes {
  private HashMap<String, ControllerInterface> routes;

  Routes() { routes = new HashMap<>(); }

  public void add(String path, ControllerInterface controller) { routes.put(path, controller); }

  public ControllerInterface get(String path) { return routes.get(path); }

  public Set<String> keySet() { return routes.keySet(); }

  public ResponseParameters getResponse(RequestParameters requestParameters) throws IOException {
    String path = requestParameters.getRequestPath();
    Boolean routeExists = routes.keySet().contains(path);
    if (routeExists) {
      return routes.get(path).getResponse(requestParameters);
    } else {
      return new ControllerFourOhFour().getResponse(requestParameters);
    }
  }
}

