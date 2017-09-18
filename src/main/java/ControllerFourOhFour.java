import java.io.IOException;

public class ControllerFourOhFour implements ControllerInterface {
  @Override
  public ResponseParameters getResponse(RequestParameters requestParameters) throws IOException {
    String fourOhFour = System.getProperty("user.dir") + "/resources/404.html";
    return new ResponseParameters
            .ResponseBuilder(404)
            .setContentType(fourOhFour)
            .setContentLength(fourOhFour)
            .setBodyType(fourOhFour)
            .setBodyContent(fourOhFour)
            .setDate()
            .build();
  }
}
