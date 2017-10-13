package org.clojars.kyleannen.javaserver;

import java.util.concurrent.ExecutorService;

public class ShutdownHook extends Thread{
  private Server httpServer;
  private ExecutorService requestExecutor;

  ShutdownHook(Server httpServer, ExecutorService requestExecutor) {
    this.httpServer = httpServer;
    this.requestExecutor = requestExecutor;
  }

  @Override
  public void run() {
    httpServer.stop();
    new Logger().log("\nShutting server down gracefully.");
    requestExecutor.shutdown();
  }
}
