package net.darkseraphim.linecount.supplier;

import java.io.File;
import java.nio.file.Path;

import net.darkseraphim.linecount.ex.LineCountException;
import net.darkseraphim.linecount.spi.supplier.FilePathSupplier;
import net.darkseraphim.linecount.Utils;

public class DefaultFilePathSupplier implements FilePathSupplier {

  private String fileName;

  @Override
  public FilePathSupplier with(String fileName) throws LineCountException {
    if (Utils.isEmpty(fileName)) {
      throw new LineCountException("Filename is empty");
    }
    this.fileName = fileName;
    return this;
  }

  @Override
  public Path get() throws LineCountException {
    File file = new File(fileName);
    if (!file.exists() || !file.isFile()) {
      throw new LineCountException("File does not exist ◕_◕");
    }
    return file.toPath();
  }
}
