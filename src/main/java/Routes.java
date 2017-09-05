import java.io.IOException;
import java.util.HashMap;

public class Routes {
  private HashMap<String, ControllerInterface> routes;

  Routes() {
    routes = new HashMap<>();
  }

  public void add(String path, ControllerInterface controller) {
    routes.put(path, controller);
  }

  public ResponseParameters getResponse(
          String path,
          RequestParameters requestParameters) throws IOException {
    Boolean routeExists = routes.keySet().contains(path);
    if (routeExists) {
      return routes.get(path).getResponse(requestParameters);
    } else {
      return new ControllerFourOhFour().getResponse(requestParameters);
    }
  }
}

