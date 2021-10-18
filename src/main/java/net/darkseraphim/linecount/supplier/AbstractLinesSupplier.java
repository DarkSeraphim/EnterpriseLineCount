package net.darkseraphim.linecount.supplier;

import net.darkseraphim.linecount.FileIterable;
import net.darkseraphim.linecount.ex.LineCountException;

import java.io.IOException;
import java.nio.file.Path;

public abstract class AbstractLinesSupplier implements LinesSupplier {
    public abstract Path asPath() throws LineCountException;

    public Iterable<Character> asIterable() throws LineCountException {
        try {
            return new FileIterable(this.asPath());
        } catch (IOException e) {
            throw new LineCountException(e);
        }
    }

    public String asString() throws LineCountException {
        StringBuilder sb = new StringBuilder();
        this.asIterable().forEach(sb::append);
        return sb.toString();
    }
}
