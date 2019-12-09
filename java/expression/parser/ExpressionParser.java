package expression.parser;

import expression.*;

import java.util.List;

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
            CommonExpression ret = parseOperand();
            expect('\0');

            return ret;
        }

        private CommonExpression parseOperand() {
            return parseBitwiseShift();
        }

        private CommonExpression parseBitwiseShift() {
            CommonExpression left = parseAddSub();

            while (true) {
                skipWhitespace();
                if (test('<')) {
                    expect('<');
                    left = new LeftShift(left, parseAddSub());
                } else if (test('>')) {
                    expect('>');
                    left = new RightShift(left, parseAddSub());
                } else {
                    return left;
                }
            }
        }

        private CommonExpression parseAddSub() {
            CommonExpression left = parseMulDiv();

            while (true) {
                skipWhitespace();
                if (test('+')) {
                    left = new Add(left, parseMulDiv());
                } else if (test('-')) {
                    left = new Subtract(left, parseMulDiv());
                } else {
                    return left;
                }
            }
        }

        private CommonExpression parseMulDiv() {
            CommonExpression left = parseValue();

            while (true) {
                skipWhitespace();
                if (test('*')) {
                    left =  new Multiply(left, parseValue());
                } else if (test('/')) {
                    left = new Divide(left, parseValue());
                } else {
                    return left;
                }
            }
        }

        private CommonExpression parseValue() {
            skipWhitespace();

            if (test('(')) {
                CommonExpression tmp = parseOperand();
                expect(')');
                return tmp;
            } else if (test('-')) {
                return new Negate(parseValue());
            } else {
                for (char var : List.of('x', 'y', 'z')) {
                    if (test(var)) {
                        return new Variable(String.valueOf(var));
                    }
                }
                return parseNumber();
            }
        }
    }
}
