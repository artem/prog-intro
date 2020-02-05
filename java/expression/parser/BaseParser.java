package expression.parser;

import expression.Const;
import expression.exceptions.ParserException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public abstract class BaseParser {
    private final ExpressionSource source;
    protected char ch;

    protected BaseParser(final ExpressionSource source) {
        this.source = source;
        nextChar();
    }

    protected void nextChar() {
        ch = source.hasNext() ? source.next() : '\0';
    }

    protected boolean test(char expected) {
        if (ch == expected) {
            nextChar();
            return true;
        }
        return false;
    }

    protected void expect(final char c) {
        if (ch != c) {
            String expect = c == 0 ? "end of expression" : "'" + c + "'";
            throw error("Expected " + expect + ", found '" + ch + "'");
        }
        nextChar();
    }

    protected void expect(final String value) {
        for (char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected Const parseNumber(boolean positive) {
        final StringBuilder sb = new StringBuilder(positive ? "" : "-");
        copyInteger(sb);

        try {
            return new Const(Integer.parseInt(sb.toString()));
        } catch (NumberFormatException e) {
            throw error("Invalid number " + sb);
        }
    }

    private void copyDigits(final StringBuilder sb) {
        while (between('0', '9')) {
            sb.append(ch);
            nextChar();
        }
    }

    private void copyInteger(final StringBuilder sb) {
        if (test('0')) {
            sb.append('0');
        } else if (between('0', '9')) {
            copyDigits(sb);
        } else {
            throw error("Number is invalid or missing");
        }
    }

    protected void skipWhitespace() {
        while (test(' ') || test('\t')) {
            // skip
        }
    }

    protected ParserException error(final String message) {
        return source.error(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }
}
