package net.darkseraphim.linecount.ex;

public class LineCountException extends Exception {

    public LineCountException(String message) {
        super(message);
    }

    public LineCountException(Exception cause) {
        super(cause);
    }

    public LineCountException(String message, Exception cause) {
        super(message, cause);
    }

}
