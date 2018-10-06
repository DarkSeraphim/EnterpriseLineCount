package net.darkseraphim.linecount.spi.strategy;

import java.math.BigInteger;
import java.nio.file.Path;

import net.darkseraphim.linecount.ex.LineCountException;

public interface LineCountStrategy {

    public BigInteger countLines(Path path) throws LineCountException;

}
