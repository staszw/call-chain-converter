package exceptions;

public class ParsingException extends RuntimeException {
    public ParsingException() {
        super("SYNTAX ERROR");
    }
}
