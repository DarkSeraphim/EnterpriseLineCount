package net.darkseraphim.linecount.strategy.delegating;

import net.darkseraphim.linecount.ex.LineCountException;
import net.darkseraphim.linecount.strategy.AsyncLineCountStrategy;
import net.darkseraphim.linecount.strategy.LineCountStrategy;
import net.darkseraphim.linecount.supplier.LinesSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

public class AsyncDelegatingLineCountStrategy implements AsyncLineCountStrategy {
    public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final LineCountStrategy delegate;

    public AsyncDelegatingLineCountStrategy(LineCountStrategy delegate) {
        this.delegate = delegate;
    }

    @Override
    public CompletableFuture<BigInteger> countLinesAsync(LinesSupplier linesSupplier) throws LineCountException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return delegate.countLines(linesSupplier);
            } catch (LineCountException e) {
                LOGGER.error("Failure in AsyncDelegatingLineCountStrategy:", e);
                throw new RuntimeException(e);
            }
        });
    }
}
