package net.darkseraphim.linecount.strategy.content;

import net.darkseraphim.linecount.ex.LineCountException;
import net.darkseraphim.linecount.supplier.LinesSupplier;

import java.math.BigInteger;
import java.util.stream.StreamSupport;

public class DefaultLineCountStrategy implements ContentBasedLineCountStrategy {

    @Override
    public BigInteger countLines(LinesSupplier linesSupplier) throws LineCountException {
        Iterable<Character> content = linesSupplier.asIterable();
        long lines = StreamSupport.stream(content.spliterator(), false)
                .filter(c -> c == '\n')
                .count();
        return BigInteger.valueOf(lines);
    }
}
