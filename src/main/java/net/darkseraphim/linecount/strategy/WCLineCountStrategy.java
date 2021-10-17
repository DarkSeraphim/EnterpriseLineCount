package net.darkseraphim.linecount.strategy;

import net.darkseraphim.linecount.ex.LineCountException;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class WCLineCountStrategy implements LineCountStrategy {
    public BigInteger countLines(Path path) throws LineCountException {
        Process process;
        try {
            process = new ProcessBuilder("wc", "-l", path.toString())
                    .start();
            if (process.waitFor() != 0) {
                throw new IOException(readOutput(process.getErrorStream()));
            }
        } catch (InterruptedException | IOException ex) {
            throw new LineCountException(ex);
        }
        return new BigInteger(readOutput(process.getInputStream()));
    }

    private static String readOutput(InputStream in) throws LineCountException {
        BufferedInputStream bis = new BufferedInputStream(in);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        String output;
        try {
            int result = bis.read();
            while(result != -1) {
                buf.write((byte) result);
                result = bis.read();
            }
            // TODO: use system charset
            output = buf.toString(StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            throw new LineCountException(e);
        }
        return output;
    }
}
