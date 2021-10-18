package net.darkseraphim.linecount.supplier;

import net.darkseraphim.linecount.FileIterable;
import net.darkseraphim.linecount.ex.LineCountException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class PathBasedLinesSupplier implements LinesSupplier {
    private final Path path;

    private PathBasedLinesSupplier(Path path) {
        this.path = path;
    }

    public static LinesSupplier forPath(Path path) throws LineCountException {
        return new PathBasedLinesSupplier(path);
    }

    public static LinesSupplier forPath(String path) throws LineCountException {
        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            throw new LineCountException("File does not exist ◕_◕");
        }
        return PathBasedLinesSupplier.forPath(file.toPath());
    }

    @Override
    public Path asPath() throws LineCountException {
        return this.path;
    }

    @Override
    public Iterable<Character> asIterable() throws LineCountException {
        try {
            return new FileIterable(this.asPath());
        } catch (IOException e) {
            throw new LineCountException(e);
        }
    }

    @Override
    public String asString() throws LineCountException {
        StringBuilder sb = new StringBuilder();
        this.asIterable().forEach(sb::append);
        return sb.toString();
    }
}
