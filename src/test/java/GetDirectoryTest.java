import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GetDirectoryTest {


  @Test
  void getDirectoryListingTest() throws IOException {
    String testRelativePath = "/";
    GetDirectory getDirectory = new GetDirectory();
    String actualResult = getDirectory.getDirectoryListing("/");
    String expectedResult =
            "<h1>/</h1><ul><li><a href='/404.html'>404.html</a></li><li><a href='/helloworld'>helloworld</a></li><li><a href='/images'>images</a></li><li><a href='/ping'>ping</a></li></ul>\r\n";
    assertEquals(expectedResult, actualResult);
  }

  @Test
  void getDirectoryListingTestImagePath() throws IOException {
    String testRelativePath = "/";
    GetDirectory getDirectory = new GetDirectory();
    String actualResult = getDirectory.getDirectoryListing("/images");
    String expectedResult =
            "<h1>/images</h1><ul><li><a href='/images/test'>test</a></li><li>turing_test.png</li></ul>\r\n";
    assertEquals(expectedResult, actualResult);
  }

  @Test
  void getDirectoryListingEmptyDirectory() throws IOException {
    String relativePath = "/images/test/empty";
    GetDirectory getDirectory = new GetDirectory();
    String actualResult2 = getDirectory.getDirectoryListing(relativePath);
    String expectedResult2 = "<h1>/images/test/empty</h1><ul><li>There are no files in this directory</li>";
    assertEquals(expectedResult2, actualResult2);
  }
}