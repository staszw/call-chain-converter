package parser;

public interface ExpressionSource {
    boolean hasNext();
    boolean hasNext(int delta);
    char next();
    char next(int delta);
}
