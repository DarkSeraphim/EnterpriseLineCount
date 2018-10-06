package net.darkseraphim.linecount.strategy;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;

public interface LineCountStrategy {

    BigInteger countLines(Path path) throws IOException;

}
