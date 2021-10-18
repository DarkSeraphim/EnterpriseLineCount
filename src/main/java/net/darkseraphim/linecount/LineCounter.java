package net.darkseraphim.linecount;

import net.darkseraphim.linecount.ex.LineCountException;
import net.darkseraphim.linecount.strategy.content.DefaultLineCountStrategy;
import net.darkseraphim.linecount.strategy.LineCountStrategy;
import net.darkseraphim.linecount.supplier.LinesSupplier;
import net.darkseraphim.linecount.supplier.PathBasedLinesSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

public class LineCounter {
  private static final Logger LOGGER = LoggerFactory.getLogger(LineCounter.class);

  public static void main(String[] args) {
    LOGGER.info("Initializing Line Counter");
    if (args.length != 1) {
      panic("Expected single file argument");
    }

    LinesSupplier linesSupplier;
    try {
      linesSupplier = PathBasedLinesSupplier.forPath(args[0]);
    } catch (LineCountException e) {
      panic(e.getMessage());
      return;
    }

    LineCountStrategy strategy = new DefaultLineCountStrategy();
    try {
      BigInteger count = strategy.countLines(linesSupplier);
      LOGGER.info("Count result: {}", count);
    } catch (LineCountException ex) {
      panic(ex.getMessage());
    }
  }

  private static void panic(String message) {
    LOGGER.error(message);
    System.exit(1);
  }
}
