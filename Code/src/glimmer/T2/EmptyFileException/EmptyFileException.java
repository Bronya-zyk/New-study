package glimmer.T2.EmptyFileException;

public class EmptyFileException extends Exception {
    public EmptyFileException() {
        super();
    }
    public EmptyFileException(String message) {
        super(message);
    }
    public EmptyFileException(String message, Throwable cause) {
        super(message, cause);
    }
}