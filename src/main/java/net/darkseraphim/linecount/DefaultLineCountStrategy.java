package net.darkseraphim.linecount;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.StreamSupport;
import java.math.BigInteger;

public class DefaultLineCountStrategy implements LineCountStrategy {

    public BigInteger countLines(Path path) throws IOException {
        FileIterable iterable = new FileIterable(path); 
        long lines = StreamSupport.stream(iterable.spliterator(), false) 
                                  .filter(c -> c == '\n') 
                                  .count(); 
        return BigInteger.valueOf(lines);
    }
}
