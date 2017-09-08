class ConfigRoutes {
  private Router router;

  ConfigRoutes(Router router) {
    this.router = router;
    this.initialize();
  }

  private void initialize() {
    this.initializeGetRoutes();
    this.initializePostRoutes();
  }

  private void initializeGetRoutes() {
    ControllerFile controllerFile = new ControllerFile();
    router.addRoute("GET","/resources/form/index.html", controllerFile);
  }

  private void initializePostRoutes() {
    ControllerPost controllerPost = new ControllerPost();
    router.addRoute("POST", "/resources/form", controllerPost);
  }
}
