package net.darkseraphim.linecount;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.stream.StreamSupport;

public class LineCounter {
  public static void main(String[] args) {
    if (args.length != 1) {
      panic("Expected single file argument");
    }
    File file = new File(args[0]);
    if (!file.exists() || !file.isFile()) {
      panic("File does not exist ◕_◕");
    }

    LineCountStrategy strategy = new DefaultLineCountStrategy();
    try {
      BigInteger count = strategy.countLines(file.toPath());
      System.out.println(count);
    } catch (IOException ex) {
      panic(ex.getMessage());
    }
  }

  private static void panic(String message) {
    System.out.println(message);
    System.exit(1);
  }
}
