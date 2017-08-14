public class App {
  public static void main(String[] args) {
    Server httpServer = new Server(args);
    httpServer.run();
  }
}
