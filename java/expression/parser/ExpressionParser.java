package expression.parser;

import expression.Const;
import expression.Expression;
import expression.TripleExpression;

public class ExpressionParser implements Parser {
    @Override
    public TripleExpression parse(final String source) {
        return parse(new StringSource(source));
    }

    private TripleExpression parse(StringSource expression) {
        return (new InternalParser(expression)).parse();
    }

    private static class InternalParser extends BaseParser {
        protected InternalParser(ExpressionSource source) {
            super(source);
            nextChar();
        }

        public TripleExpression parse() {
            //final Const
            return null;
        }
    }
}
