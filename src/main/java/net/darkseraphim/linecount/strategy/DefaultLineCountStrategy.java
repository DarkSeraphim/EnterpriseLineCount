package net.darkseraphim.linecount.strategy;

import net.darkseraphim.linecount.FileIterable;
import net.darkseraphim.linecount.ex.LineCountException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.StreamSupport;
import java.math.BigInteger;

public class DefaultLineCountStrategy implements LineCountStrategy {

    public BigInteger countLines(Path path) throws LineCountException {
        FileIterable iterable;
        try {
            iterable = new FileIterable(path);
        } catch (IOException e) {
            throw new LineCountException(e);
        }
        long lines = StreamSupport.stream(iterable.spliterator(), false) 
                                  .filter(c -> c == '\n') 
                                  .count(); 
        return BigInteger.valueOf(lines);
    }
}
