package net.darkseraphim.linecount;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

import net.darkseraphim.linecount.ex.LineCountException;
import net.darkseraphim.linecount.spi.strategy.LineCountStrategy;
import net.darkseraphim.linecount.spi.supplier.FilePathSupplier;
import net.darkseraphim.linecount.strategy.DefaultLineCountStrategy;
import net.darkseraphim.linecount.supplier.DefaultFilePathSupplier;

public class LineCounter {
  public static void main(String[] args) {
      if (args.length != 1) {
          panic("Expected single file argument");
      }

      FilePathSupplier filePathSupplier = load(FilePathSupplier.class)
                                           .orElseGet(() -> {
                                             return new DefaultFilePathSupplier();
                                           });
      try {
          filePathSupplier = filePathSupplier.with(args[0]);
      } catch (LineCountException e) {
          panic(e.getMessage());
          return;
      }

      LineCountStrategy strategy = load(LineCountStrategy.class)
                                    .orElseGet(() -> {
                                      return new DefaultLineCountStrategy();
                                    });
      try {
          BigInteger count = strategy.countLines(filePathSupplier.get());
          System.out.println(count);
      } catch (LineCountException ex) {
          panic(ex.getMessage());
      }
  }

  private static <S> Optional<S> load(Class<S> cls) {
      // TODO: support flag-based forced implementations
      return ServiceLoader.load(cls)
                .stream()
                .map(LineCounter::loadSilent)
                .filter(Objects::nonNull)
                .findFirst();
  }

  private static <S> S loadSilent(ServiceLoader.Provider<S> provider) {
    try {
      return provider.get();
    } catch (ServiceConfigurationError ex) {
      ex.printStackTrace(); // TODO: logging, with fallback to stderr
    }
    return null;
  }

  private static void panic(String message) {
      System.out.println(message);
      System.exit(1);
  }
}
