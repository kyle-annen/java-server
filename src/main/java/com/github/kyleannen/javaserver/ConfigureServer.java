package com.github.kyleannen.javaserver;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConfigureServer {

  public Server configure(String[] args, Router router) {
    ReadRequest readRequest = new ReadRequest();
    SendResponse sendResponse = new SendResponse();
    Logger logger = new Logger();
    ExecutorService requestExecutor = Executors.newFixedThreadPool(10);

    Server server = new Server(
      args,
      requestExecutor,
      readRequest,
      sendResponse,
      router,
      logger);

    Runtime.getRuntime().addShutdownHook(new ShutdownHook(server, requestExecutor));

    return server;
  }
}
