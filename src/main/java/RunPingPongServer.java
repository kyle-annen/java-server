public class RunPingPongServer {
    public static void main(String [] args) throws Exception {
        PingPongServer PingPongServerInstance = new PingPongServer(args);
        PingPongServerInstance.run();
    }
}
