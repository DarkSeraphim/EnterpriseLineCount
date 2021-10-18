package net.darkseraphim.linecount.strategy.lineendingdetection;

import java.util.Optional;

import net.darkseraphim.linecount.LineEnding;
import net.darkseraphim.linecount.strategy.LineEndingDetectionStrategy;
import net.darkseraphim.linecount.supplier.LinesSupplier;

public class OsBasedLineEndingDetection implements LineEndingDetectionStrategy {
    @Override
    public Optional<LineEnding> detectLineEnding(LinesSupplier linesSupplier) {
        String os = System.getProperty("os.name", "generic").toLowerCase();
        if (os.contains("mac") || os.contains("darwin")) {
            return Optional.of(LineEnding.CR);
        } else if (os.contains("win")) {
            return Optional.of(LineEnding.CRLF);
        } else if (os.contains("nux")) {
            return Optional.of(LineEnding.LF);
        }
        return Optional.empty();
    }
}
