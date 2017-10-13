package org.clojars.kyleannen.javaserver;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestDirectorySetup {



  @BeforeAll
  static void generateTestFileStructure() throws IOException {
    List<String> lines = Arrays.asList("test\r\n\r\ntest","test\n");
    File dir = new File("./TestDirectory");
    File dir2 = new File("./TestEmpty");
    File dir3 = new File("./TestPng");
    File dir4 = new File("./TestAllFiles");
    dir.mkdir();
    dir2.mkdir();
    dir3.mkdir();
    dir4.mkdir();

    Path file1 = Paths.get("./TestDirectory/testFile1.txt");
    Path file2 = Paths.get("./TestPng/test.png");
    Path file3 = Paths.get("./TestAllFiles/test.jpeg");
    Path file4 = Paths.get("./TestAllFiles/test.html");
    Path file5 = Paths.get("./TestAllFiles/test.txt");
    Path file6 = Paths.get("./TestAllFiles/test.png");
    Path file7 = Paths.get("./TestAllFiles/test.pdf");
    Path file8 = Paths.get("./TestAllFiles/test.js");
    Path file9 = Paths.get("./TestAllFiles/test.css");
    Path file10 = Paths.get("./TestAllFiles/test.jpg");
    Path file11 = Paths.get("./TestAllFiles/test.gif");
    Files.write(file1, lines);
    Files.write(file2, lines);
    Files.write(file3, lines);
    Files.write(file4, lines);
    Files.write(file5, lines);
    Files.write(file6, lines);
    Files.write(file7, lines);
    Files.write(file8, lines);
    Files.write(file9, lines);
    Files.write(file10, lines);
    Files.write(file11, lines);


  }

  @AfterAll
  static void destroyTestFileStructure() throws IOException {
    ArrayList<File> files = new ArrayList<>();
    File dir = new File("./TestDirectory");
    File dir2 = new File("./TestEmpty");
    File dir3 = new File("./TestPng");
    dir.delete();
    dir2.delete();
    dir3.delete();

    File file1 = new File("./TestDirectory/testFile1.txt");
    File file2 = new File("./TestPng/test.png");
    File file3 = new File("./TestAllFiles/test.jpeg");
    File file4 = new File("./TestAllFiles/test.html");
    File file5 = new File("./TestAllFiles/test.txt");
    File file6 = new File("./TestAllFiles/test.png");
    File file7 = new File("./TestAllFiles/test.pdf");
    File file8 = new File("./TestAllFiles/test.js");
    File file9 = new File("./TestAllFiles/test.css");
    File file10 = new File("./TestAllFiles/test.jpg");
    File file11 = new File("./TestAllFiles/test.gif");
    files.add(file1);
    files.add(file2);
    files.add(file3);
    files.add(file4);
    files.add(file5);
    files.add(file6);
    files.add(file7);
    files.add(file8);
    files.add(file9);
    files.add(file10);
    files.add(file11);

    for(File testfile : files) {
      testfile.delete();
    }

    File dir4 = new File("./TestAllFiles");
    dir4.delete();
  }
}
