package net.darkseraphim.linecount.supplier;

import net.darkseraphim.linecount.ex.LineCountException;

import java.nio.file.Path;

public class IterableBasedLinesSupplier extends AbstractLinesSupplier {
    private final Iterable<Character> iterable;

    private IterableBasedLinesSupplier(Iterable<Character> iterable) {
        this.iterable = iterable;
    }

    public static IterableBasedLinesSupplier forIterable(Iterable<Character> iterable) {
        return new IterableBasedLinesSupplier(iterable);
    }

    @Override
    public Path asPath() throws LineCountException {
        throw new LineCountException("asPath is not supported for IterableBasedLinesSupplier");
    }

    @Override
    public Iterable<Character> asIterable() throws LineCountException {
        return this.iterable;
    }
}
