package org.clojars.kyleannen.javaserver;

import java.io.IOException;
import java.net.Socket;

public interface SendInterface {
  void send(ResponseParameters responseParameters, Socket socket) throws IOException;
}
