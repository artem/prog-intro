package expression;

public abstract class CommonExpression implements Expression, DoubleExpression, TripleExpression {
    @Override
    public int evaluate(int x) {
        return evaluate(x, 0, 0);
    }
}
