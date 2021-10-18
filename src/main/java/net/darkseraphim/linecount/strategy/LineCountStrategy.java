package net.darkseraphim.linecount.strategy;

import net.darkseraphim.linecount.ex.LineCountException;
import net.darkseraphim.linecount.supplier.LinesSupplier;

import java.math.BigInteger;

public interface LineCountStrategy {

    BigInteger countLines(LinesSupplier linesSupplier) throws LineCountException;

}
