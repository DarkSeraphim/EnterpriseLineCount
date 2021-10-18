package net.darkseraphim.linecount.strategy;

import net.darkseraphim.linecount.ex.LineCountException;
import net.darkseraphim.linecount.supplier.LinesSupplier;
import net.darkseraphim.linecount.LineEnding;

import java.math.BigInteger;

public interface LineCountStrategy {

    BigInteger countLines(LinesSupplier linesSupplier, LineEnding lineEnding) throws LineCountException;

}
