package net.darkseraphim.linecount.strategy;

import net.darkseraphim.linecount.ex.LineCountException;
import net.darkseraphim.linecount.supplier.LinesSupplier;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface AsyncLineCountStrategy extends LineCountStrategy {
    @Override
    default BigInteger countLines(LinesSupplier linesSupplier) throws LineCountException {
        try {
            return this.countLinesAsync(linesSupplier).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new LineCountException(e);
        }
    }

    CompletableFuture<BigInteger> countLinesAsync(LinesSupplier linesSupplier) throws LineCountException;
}
