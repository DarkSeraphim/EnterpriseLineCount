package net.darkseraphim.linecount.supplier;

import net.darkseraphim.linecount.ex.LineCountException;

import java.nio.file.Path;

public interface FilePathSupplier {

    /**
     * Initialize the supplier
     * @param fileName the filename to initialize with
     * @return this
     */
    FilePathSupplier with(String fileName) throws LineCountException;

    /**
     * Get the file Path
     * @return the Path
     * @throws LineCountException if the file does not exist
     */
    Path get() throws LineCountException;

}
