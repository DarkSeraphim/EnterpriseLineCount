package net.darkseraphim.linecount;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.lang.ref.Cleaner;
import java.util.Iterator;

public class FileIterable implements Iterable<Character> {

    private static final Cleaner CLEANER = Cleaner.create();

    private final Path path;

    public FileIterable(String path) throws IOException {
        this(new File(path));
    }

    public FileIterable(File file) throws IOException {
        this(file.toPath());
    }

    public FileIterable(Path path) throws IOException {
        this.path = path;
    }

    public Iterator<Character> iterator() {
        final Iterator<Character> iterator;
        try {
            InputStream input = Files.newInputStream(path, 
                                                     StandardOpenOption.READ);
            InputStreamReader reader = new InputStreamReader(input);

            iterator = new Iterator<Character>() {
                private int current = -1;

                @Override
                public boolean hasNext() {
                    if (current < 0) {
                        try {
                            current = reader.read();
                        } catch (IOException ex) {
                          throw new RuntimeException(ex);
                        }
                    }
                    return current >= 0;
                }

                @Override
                public Character next() {
                    int old = this.current;
                    this.current = -1;
                    return (char) old;
                }
            };
            CLEANER.register(iterator, () -> {
              try {
                reader.close();
              } catch (IOException ex) {
                // Ignore
              }
            });
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }

        return iterator;
    }  
}
