package com.github.kyleannen.javaserver;

import com.github.kyleannen.javaserver.Logger;
import org.junit.jupiter.api.Test;

public class LoggerTest {

  @Test
  void loggerCanBeInitialized() {
    try {
      Logger logger = new Logger();
    } catch (Exception e) {
      assert(false);
    }
  }

  @Test
  void loggerLogCanBeCalled() {
    Logger logger = new Logger();
    try {
      logger.log("");
      assert(true);
    } catch (Exception e) {
      assert(false);
    }
  }
}
