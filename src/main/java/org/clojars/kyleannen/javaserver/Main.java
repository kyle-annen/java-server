package org.clojars.kyleannen.javaserver;

public class Main {
  public static void main(String[] args) {
    Router router = new Router();
    Server httpServer = new ConfigureServer().configure(args, router);
    httpServer.run();
  }
}