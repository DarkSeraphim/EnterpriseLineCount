package net.darkseraphim.linecount;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;

public interface LineCountStrategy {

    public BigInteger countLines(Path path) throws IOException;

}
