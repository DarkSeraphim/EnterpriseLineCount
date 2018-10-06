module LineCountStrategy.wc {
  requires LineCounter
  provides net.darkseraphim.linecount.spi.strategy.LineCountStrategy with net.darkseraphim.linecount.strategy.WCLineCountStrategy
}
