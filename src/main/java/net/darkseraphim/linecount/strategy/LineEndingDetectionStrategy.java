package net.darkseraphim.linecount.strategy;

import java.util.Optional;

import net.darkseraphim.linecount.supplier.LinesSupplier;
import net.darkseraphim.linecount.LineEnding;


public interface LineEndingDetectionStrategy {
    Optional<LineEnding> detectLineEnding(LinesSupplier linesSupplier);
}
