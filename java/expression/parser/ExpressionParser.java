package expression.parser;

import expression.*;

public class ExpressionParser implements Parser {
    @Override
    public CommonExpression parse(final String source) {
        return parse(new StringSource(source));
    }

    private CommonExpression parse(StringSource expression) {
        return new InternalParser(expression).parse();
    }

    private static class InternalParser extends BaseParser {
        protected InternalParser(ExpressionSource source) {
            super(source);
        }

        public CommonExpression parse() {
            return parse(false);
        }

        private CommonExpression parse(boolean nested) {
            CommonExpression ret = parseElement();

            while ((nested && !test(')')) || (!nested && !test('\0')) ) {
                ret = parseOperation(ret);
            }

            return ret;
        }

        private CommonExpression parseOperation(CommonExpression ret) {
            if (test('+')) {
                return new Add(ret, parseElement());
            } else if (test('-')) {
                return new Subtract(ret, parseElement());
            } else if (test('*')) {
                return new Multiply(ret, parseElement());
            } else if (test('/')) {
                return new Divide(ret, parseElement());
            } else {
                throw error("Unsupported operation!");
            }
        }

        private CommonExpression parseElement() {
            CommonExpression ret;
            skipWhitespace();
            ret = parseValue();
            skipWhitespace();
            return ret;
        }

        private CommonExpression parseValue() {
            if (test('(')) {
                return parse(true);
            } else if (test('x')) {
                return new Variable("x");
            } else if (test('y')) {
                return new Variable("y");
            } else if (test('z')) {
                return new Variable("z");
            } else {
                return parseNumber();
            }
        }

        private Const parseNumber() {
            final StringBuilder sb = new StringBuilder();
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
            if (test('-')) {
                sb.append('-');
                skipWhitespace();
            }
            if (test('0')) {
                sb.append('0');
            } else if (between('1', '9')) {
                copyDigits(sb);
            } else {
                throw error("Invalid number");
            }
        }

        private void skipWhitespace() {
            while (test(' ')) {
                // skip
            }
        }
    }
}
