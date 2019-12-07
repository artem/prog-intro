package expression;

public abstract class Evaluable implements Expression, DoubleExpression, TripleExpression {
    @Override
    public int evaluate(int x) {
        return evaluate(x, 0, 0);
    }
}
