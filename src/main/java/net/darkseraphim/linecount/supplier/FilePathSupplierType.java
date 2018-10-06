package net.darkseraphim.linecount.supplier;

import net.darkseraphim.linecount.ex.LineCountException;

import java.util.function.Supplier;

public enum FilePathSupplierType {

  DEFAULT(DefaultFilePathSupplier::new);

  private final Supplier<FilePathSupplier> constructor;

  FilePathSupplierType(Supplier<FilePathSupplier> constructor) {
    this.constructor = constructor;
  }

  public FilePathSupplier instanceForFileName(String fileName) throws LineCountException {
    FilePathSupplier supplier = constructor.get();
    return supplier.with(fileName);
  }

}
