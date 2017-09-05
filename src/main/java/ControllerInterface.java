import java.io.IOException;

public interface ControllerInterface {
  ResponseParameters getResponse(RequestParameters requestParameters) throws IOException;
}
