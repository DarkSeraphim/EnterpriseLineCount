package net.darkseraphim.linecount.supplier;

import net.darkseraphim.linecount.ex.LineCountException;

import java.io.File;
import java.nio.file.Path;

public class PathBasedLinesSupplier extends AbstractLinesSupplier {
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
            throw new LineCountException("File does not exist ◕_◕: " + file.getAbsolutePath());
        }
        return PathBasedLinesSupplier.forPath(file.toPath());
    }

    @Override
    public Path asPath() throws LineCountException {
        return this.path;
    }

}
