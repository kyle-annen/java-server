package com.github.kyleannen.javaserver;

import java.io.IOException;
import java.net.Socket;

public interface ReadInterface {
  RequestParameters getRequest(Socket socket, String directoryPath) throws IOException;
}
