import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;

public class RequestHandler implements Runnable {
  private String directoryPath;
  Socket socket;
  private Boolean threadAlive = true;
  private Logger logger;

  RequestHandler(String _directoryPath, Socket _socket, Logger _logger) {
    directoryPath = _directoryPath;
    socket = _socket;
    logger = _logger;
  }

  public void run() {
    while (threadAlive) {
      try {
        InputStreamReader inputStreamReader =
                new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader =
                new BufferedReader(inputStreamReader);

        ArrayList<String> httpMessage =
                this.readHttpMessage(bufferedReader);
        for(String m: httpMessage) {
          System.out.println(m);
        }

        RequestParameters requestParams =
                new RequestParameters.RequestBuilder(directoryPath)
                        .setHttpVerb(httpMessage)
                        .setRequestPath(httpMessage)
                        .setHost(httpMessage)
                        .setUserAgent(httpMessage)
                        .setAccept(httpMessage)
                        .setSocket(socket)
                        .build();

        //instantiate the router with the directory path
        MethodRouter httpRouter = new MethodRouter();

        ResponseParameters responseParams =
                httpRouter.getResponse(requestParams);

        new SendResponse().send(responseParams, socket);

        this.closeConnections(bufferedReader, inputStreamReader, socket);
      } catch (IOException | ParseException e) {
        logger.log(e.toString());
        threadAlive = false;

      }
    }
  }

  private void closeConnections(
          BufferedReader bufferedReader,
          InputStreamReader inputStreamReader,
          Socket socket) throws IOException {
    bufferedReader.close();
    inputStreamReader.close();
    socket.close();
  }

  private ArrayList<String> readHttpMessage(
          BufferedReader bufferedReader ) throws IOException {
    Boolean reading = true;
    ArrayList<String> httpMessage = new ArrayList<>();
    String line;
    while(reading) {
      line = bufferedReader.readLine();
      if(line.equals("")) { reading = false; }
      else { httpMessage.add(line); }
    }
    return httpMessage;
  }
}
