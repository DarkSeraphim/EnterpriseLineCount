package net.darkseraphim.linecount.supplier;

import net.darkseraphim.linecount.ex.LineCountException;

import java.nio.file.Path;

public interface LinesSupplier {
    Path asPath() throws LineCountException;
    Iterable<Character> asIterable() throws LineCountException;
    String asString() throws LineCountException;
}
