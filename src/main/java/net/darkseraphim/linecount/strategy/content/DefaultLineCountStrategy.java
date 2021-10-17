package net.darkseraphim.linecount.strategy.content;

import net.darkseraphim.linecount.ex.LineCountException;

import java.math.BigInteger;
import java.util.stream.StreamSupport;

public class DefaultLineCountStrategy extends ContentBasedLineCountStrategy {

    @Override
    BigInteger countLines(Iterable<Character> content) throws LineCountException {
        long lines = StreamSupport.stream(content.spliterator(), false)
                .filter(c -> c == '\n')
                .count();
        return BigInteger.valueOf(lines);
    }
}
