package net.darkseraphim.linecount.strategy;

import net.darkseraphim.linecount.ex.LineCountException;
import net.darkseraphim.linecount.supplier.LinesSupplier;
import net.darkseraphim.linecount.LineEnding;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface AsyncLineCountStrategy extends LineCountStrategy {
    @Override
    default BigInteger countLines(LinesSupplier linesSupplier, LineEnding lineEnding) throws LineCountException {
        try {
            return this.countLinesAsync(linesSupplier, lineEnding).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new LineCountException(e);
        }
    }

    CompletableFuture<BigInteger> countLinesAsync(LinesSupplier linesSupplier, LineEnding lineEnding) throws LineCountException;
}
