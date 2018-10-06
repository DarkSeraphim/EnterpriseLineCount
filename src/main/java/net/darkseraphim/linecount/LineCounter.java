package net.darkseraphim.linecount;

import net.darkseraphim.linecount.ex.LineCountException;
import net.darkseraphim.linecount.strategy.DefaultLineCountStrategy;
import net.darkseraphim.linecount.strategy.LineCountStrategy;
import net.darkseraphim.linecount.supplier.FilePathSupplier;
import net.darkseraphim.linecount.supplier.FilePathSupplierType;

import java.io.IOException;
import java.math.BigInteger;

public class LineCounter {
  public static void main(String[] args) {
    if (args.length != 1) {
      panic("Expected single file argument");
    }

    FilePathSupplierType type = FilePathSupplierType.DEFAULT;

    FilePathSupplier filePathSupplier;
    try {
      filePathSupplier = type.instanceForFileName(args[0]);
    } catch (LineCountException e) {
      panic(e.getMessage());
      return;
    }

    LineCountStrategy strategy = new DefaultLineCountStrategy();
    try {
      BigInteger count = strategy.countLines(filePathSupplier.get());
      System.out.println(count);
    } catch (LineCountException ex) {
      panic(ex.getMessage());
    }
  }

  private static void panic(String message) {
    System.out.println(message);
    System.exit(1);
  }
}
