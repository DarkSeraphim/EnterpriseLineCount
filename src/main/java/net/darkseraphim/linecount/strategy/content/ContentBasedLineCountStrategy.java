package net.darkseraphim.linecount.strategy.content;

import net.darkseraphim.linecount.FileIterable;
import net.darkseraphim.linecount.ex.LineCountException;
import net.darkseraphim.linecount.strategy.LineCountStrategy;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;

public abstract class ContentBasedLineCountStrategy implements LineCountStrategy {

    abstract BigInteger countLines(Iterable<Character> content) throws LineCountException;

    @Override
    public BigInteger countLines(Path path) throws LineCountException {
        FileIterable iterable;
        try {
            iterable = new FileIterable(path);
        } catch (IOException e) {
            throw new LineCountException(e);
        }
        return this.countLines(iterable);
    }
}
