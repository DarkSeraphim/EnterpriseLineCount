package net.darkseraphim.linecount.strategy;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class WCLineCountStrategy implements LineCountStrategy {
    public BigInteger countLines(Path path) throws IOException {
        Process process = new ProcessBuilder("wc", "-l", path.toString())
                                .start();
        try {
            if (process.waitFor() != 0) {
                throw new IOException(readOutput(process.getErrorStream()));
            }
        } catch (InterruptedException ex) {
            throw new IOException(ex);
        }
        return new BigInteger(readOutput(process.getInputStream()));
    }

    private static String readOutput(InputStream in) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(in);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result = bis.read();
        while(result != -1) {
            buf.write((byte) result);
            result = bis.read();
        }
        // TODO: use system charset
        return buf.toString(StandardCharsets.UTF_8.name());
    }
}
