package expression.exceptions;

public abstract class ExpressionException extends RuntimeException {
    public ExpressionException(final String message) {
        super(message);
    }
}