import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ResponseFactoryTest {

  @Test
  void getFileHeaderReturnsFileHeader() {
    ArrayList<String> fileHeader = new ResponseFactory().getFileHeader("text/html", "132");

    assertEquals("HTTP/1.1 200 OK\r\n", fileHeader.get(0));
    assertEquals("Date:", fileHeader.get(1).split(" ")[0]);
    assertEquals("ContentLength: 132\r\n", fileHeader.get(2));
    assertEquals("ContentType: text/html\r\n", fileHeader.get(3));
  }
}