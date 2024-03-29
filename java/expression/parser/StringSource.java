package expression.parser;

import expression.exceptions.ParserException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class StringSource implements ExpressionSource {
    private final String data;
    private int pos;

    public StringSource(final String data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    @Override
    public char next() {
        return data.charAt(pos++);
    }

    @Override
    public ParserException error(final String message) {
        return new ParserException(message, data.substring(0, pos), pos);
    }
}
