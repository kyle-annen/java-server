package org.clojars.kyleannen.javaserver;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfigRoutesTest {
  @Test
  void routerConfigurationsAreAdded() {
    Router router = new Router();
    new ConfigRoutes(router);
    Routes routes = router.getRoutes("GET");
    Boolean expected = true;
    Boolean actual = routes.routeExists("/resources/form/index.html");
    assertEquals(expected, actual);
  }

  @Test
  void routerConfigurationDoesNotPopulateNonexistentPaths() {
    Router router = new Router();
    new ConfigRoutes(router);
    Routes routes = router.getRoutes("POST");
    Boolean expected = false;
    Boolean actual = routes.routeExists("/this/is/an/really/bad/route.longfileextensiondoesntexistyet");
    assertEquals(expected, actual);
  }
}
