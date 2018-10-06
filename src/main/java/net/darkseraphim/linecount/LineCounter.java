package net.darkseraphim.linecount;

import java.io.IOException;
import java.math.BigInteger;

public class LineCounter {
  public static void main(String[] args) {
    if (args.length != 1) {
      panic("Expected single file argument");
    }

    FilePathSupplier filePathSupplier;
    try {
      filePathSupplier = new DefaultFilePathSupplier().with(args[0]);
    } catch (LineCountException e) {
      panic(e.getMessage());
      return;
    }

    LineCountStrategy strategy = new DefaultLineCountStrategy();
    try {
      BigInteger count = strategy.countLines(filePathSupplier.get());
      System.out.println(count);
    } catch (IOException | LineCountException ex) {
      panic(ex.getMessage());
    }
  }

  private static void panic(String message) {
    System.out.println(message);
    System.exit(1);
  }
}
