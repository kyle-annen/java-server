
import java.io.*;
import java.net.*;

public class Run {
    public static void main(String [] args) throws Exception {
        PingPongServer NewPingPongServer = new PingPongServer(args);
        NewPingPongServer.run();
    }
}
