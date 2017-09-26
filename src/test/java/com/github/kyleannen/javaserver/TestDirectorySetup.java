package com.github.kyleannen.javaserver;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class TestDirectorySetup {

  @BeforeAll
  static void generateTestFileStructure() throws IOException {
    List<String> lines = Arrays.asList("test\r\n\r\ntest","test\n");
    File dir = new File("./TestDirectory");
    dir.mkdir();
    File dir2 = new File("./TestEmpty");
    dir2.mkdir();
    File dir3 = new File("./TestPng");
    dir3.mkdir();
    Path file = Paths.get("./TestDirectory/testFile1.txt");
    Files.write(file, lines);
    Path file2 = Paths.get("./TestPng/test.png");
    Files.write(file2, lines);
  }

  @AfterAll
  static void destroyTestFileStructure() throws IOException {
    File dir = new File("./TestDirectory");
    File dir2 = new File("./TestEmpty");
    File dir3 = new File("./TestPng");
    File file = new File("./TestDirectory/testFile1.txt");
    File file2 = new File("./TestPng/test.png");
    file.delete();
    file2.delete();
    dir.delete();
    dir2.delete();
    dir3.delete();
  }
}
