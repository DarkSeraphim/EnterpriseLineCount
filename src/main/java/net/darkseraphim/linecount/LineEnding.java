package net.darkseraphim.linecount;

public enum LineEnding {
    CR("\r"),
    LF("\n"),
    CRLF("\r\n");

    private final String string;

    private LineEnding(String string) {
        this.string = string;
    }

    public String asString() {
        return this.string;
    }
}
