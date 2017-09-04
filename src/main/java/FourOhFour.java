import java.io.IOException;

public class FourOhFour {
  ResponseParameters generateFourOhFourResponse() throws IOException {
    String filePath = System.getProperty("user.dir") + "/resources/404.html";
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
