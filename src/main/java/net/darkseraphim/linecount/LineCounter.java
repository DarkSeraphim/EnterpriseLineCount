package net.darkseraphim.linecount;

import java.io.File;
import java.io.IOException;
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

    try {
      FileIterable iterable = new FileIterable(file);
      long lines = StreamSupport.stream(iterable.spliterator(), false)
                                .filter(c -> c == '\n')
                                .count();
      System.out.println(lines);
    } catch (IOException ex) {
      ex.printStackTrace();
      panic("File could not be read");
    }
  }

  private static void panic(String message) {
    System.out.println(message);
    System.exit(1);
  }
}
