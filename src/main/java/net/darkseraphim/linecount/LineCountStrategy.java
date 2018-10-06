package net.darkseraphim.linecount;

import java.math.BigInteger;
import java.nio.file.Path;

public interface LineCountStrategy {

    public BigInteger countLines(Path path) throws LineCountException;

}
