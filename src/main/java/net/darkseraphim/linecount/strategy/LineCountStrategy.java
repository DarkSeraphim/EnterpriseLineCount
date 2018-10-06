package net.darkseraphim.linecount.strategy;

import java.math.BigInteger;
import java.nio.file.Path;

public interface LineCountStrategy {

    public BigInteger countLines(Path path) throws LineCountException;

}
