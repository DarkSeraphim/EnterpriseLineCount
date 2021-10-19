package net.darkseraphim.linecount.strategy.content;

import net.darkseraphim.linecount.ex.LineCountException;
import net.darkseraphim.linecount.supplier.LinesSupplier;
import net.darkseraphim.linecount.LineEnding;
import net.darkseraphim.linecount.Utils;

import java.math.BigInteger;
import java.util.stream.Collectors;

public class DefaultLineCountStrategy implements ContentBasedLineCountStrategy {

    @Override
    public BigInteger countLines(LinesSupplier linesSupplier, LineEnding lineEnding) throws LineCountException {
        Iterable<Character> content = linesSupplier.asIterable();
        long lines = Utils.chunks(content, lineEnding.asString().length())
            .map(cs -> cs.map(Object::toString).collect(Collectors.joining()))
            .filter(s -> s.equals(lineEnding.asString()))
            .count();
        return BigInteger.valueOf(lines);
    }
}
