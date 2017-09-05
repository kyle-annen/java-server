public class RouterConfiguration {
  Router router;

  RouterConfiguration(Router router) {
    this.router = router;
  }

  void initialize() {
    this.initializeGetRoutes();
    this.initializePostRoutes();
  }

  void initializeGetRoutes() {
    ControllerFile controllerFile = new ControllerFile();
    router.addRoute("GET","/resources/form/index.html", controllerFile);
  }

  void initializePostRoutes() {
    ControllerPost controllerPost = new ControllerPost();
    router.addRoute("POST", "/resources/form", controllerPost);
  }

}
