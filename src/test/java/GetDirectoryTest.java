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
            "<h1>/</h1><ul><li>404.html</li><li>helloworld.html</li><li><a href='/images'>images</a></li><li>ping.html</li></ul>\r\n";
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
  void getDirectoryListingTestImageTestPath() throws IOException {
    String testRelativePath = "/";
    GetDirectory getDirectory = new GetDirectory();
    String actualResult = getDirectory.getDirectoryListing("/images/test");
    String expectedResult =
            "<h1>/images/test</h1><ul><li><a href='/images/test/empty'>empty</a></li><li>test.html</li></ul>\r\n";
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