package net.darkseraphim.linecount.strategy.content;

import net.darkseraphim.linecount.Utils;
import net.darkseraphim.linecount.ex.LineCountException;
import net.darkseraphim.linecount.strategy.AsyncLineCountStrategy;
import net.darkseraphim.linecount.supplier.IterableBasedLinesSupplier;
import net.darkseraphim.linecount.supplier.LinesSupplier;

import java.math.BigInteger;
import java.util.Spliterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ParallelDelegatingLineCountStrategy implements ContentBasedLineCountStrategy {
    private final AsyncLineCountStrategy delegate;

    public ParallelDelegatingLineCountStrategy(AsyncLineCountStrategy delegate) {
        this.delegate = delegate;
    }

    @Override
    public BigInteger countLines(LinesSupplier linesSupplier) throws LineCountException {
        Spliterator<Character> left = linesSupplier.asIterable().spliterator();
        Spliterator<Character> right = left.trySplit();

        LinesSupplier leftLinesSupplier = IterableBasedLinesSupplier.forIterable(Utils.spliteratorAsIterable(left));
        LinesSupplier rightLinesSupplier = IterableBasedLinesSupplier.forIterable(Utils.spliteratorAsIterable(right));

        CompletableFuture<BigInteger> leftCount = this.delegate.countLinesAsync(leftLinesSupplier);
        CompletableFuture<BigInteger> rightCount = this.delegate.countLinesAsync(rightLinesSupplier);

        try {
            return leftCount.thenCombine(rightCount, BigInteger::add).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new LineCountException(e);
        }
    }
}
