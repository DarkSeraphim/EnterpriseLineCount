package net.darkseraphim.linecount.strategy;

import net.darkseraphim.linecount.ex.LineCountException;

import java.math.BigInteger;
import java.nio.file.Path;

public interface LineCountStrategy {

    BigInteger countLines(Path path) throws LineCountException;

}
