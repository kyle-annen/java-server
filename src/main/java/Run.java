
import java.io.*;
import java.net.*;

public class Run {
    public static void main(String [] args) throws Exception {
        PingPongServer PingPongServerInstance = new PingPongServer(args);
        PingPongServerInstance.run();
    }
}
