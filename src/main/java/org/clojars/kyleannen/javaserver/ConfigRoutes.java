package org.clojars.kyleannen.javaserver;

public class ConfigRoutes {
  private Router router;

  ConfigRoutes(Router router) {
    this.router = router;
    this.initialize();
  }

  private void initialize() {
    this.initializeGetRoutes();
    this.initializePostRoutes();
    //custom routes go here:
  }

  private void initializeGetRoutes() {
    ControllerForm controllerForm = new ControllerForm();
    ControllerFile controllerFile = new ControllerFile();
    router.addRoute("GET","/resources/form/index.html", controllerFile);
    router.addRoute("GET", "/form", controllerForm);
  }

  private void initializePostRoutes() {
    ControllerPost controllerPost = new ControllerPost();
    router.addRoute("POST", "/resources/form", controllerPost);
  }

//  This is an example of a custom route, using a custom controller.
//  private void initializeCustomRoutes() {
//    ControllerCustom controllerCustom = new CustomerController();
//    router.addRoute("GET", "/get/custom/route", controllerCustom);
//  }
}
