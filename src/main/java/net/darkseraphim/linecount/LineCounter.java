package net.darkseraphim.linecount;

import net.darkseraphim.linecount.ex.LineCountException;
import net.darkseraphim.linecount.strategy.content.DefaultLineCountStrategy;
import net.darkseraphim.linecount.strategy.LineCountStrategy;
import net.darkseraphim.linecount.strategy.content.ParallelDelegatingLineCountStrategy;
import net.darkseraphim.linecount.strategy.delegating.AsyncDelegatingLineCountStrategy;
import net.darkseraphim.linecount.supplier.LinesSupplier;
import net.darkseraphim.linecount.supplier.PathBasedLinesSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.time.Duration;

public class LineCounter {
  private static final Logger LOGGER = LoggerFactory.getLogger(LineCounter.class);

  public static void main(String[] args) {
    LOGGER.info("Initializing Line Counter");
    if (args.length != 1) {
      panic("Expected single file argument");
    }

    long startTime = System.nanoTime();

    LinesSupplier linesSupplier;
    try {
      linesSupplier = PathBasedLinesSupplier.forPath(args[0]);
    } catch (LineCountException e) {
      panic(e.getMessage());
      return;
    }

    LineCountStrategy hyperParallelStrategyTree =
            new AsyncDelegatingLineCountStrategy(
                    new ParallelDelegatingLineCountStrategy(
                            new AsyncDelegatingLineCountStrategy(
                                    new DefaultLineCountStrategy()
                            )
                    )
            );

    try {
      BigInteger count = hyperParallelStrategyTree.countLines(linesSupplier);
      LOGGER.info("Count result: {}", count);
    } catch (LineCountException ex) {
      panic(ex.getMessage());
    }

    long endTime = System.nanoTime();
    Duration elapsed = Duration.ofNanos(endTime - startTime);
    LOGGER.info("Counting lines took {}.{} seconds", elapsed.toSecondsPart(), elapsed.toMillisPart());
  }

  private static void panic(String message) {
    LOGGER.error(message);
    System.exit(1);
  }
}
