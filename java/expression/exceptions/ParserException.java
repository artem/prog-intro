package expression.exceptions;

public class ParserException extends ExpressionException {
    public ParserException(String message, int pos) {
        super(pos + ": " + message);
    }
}
