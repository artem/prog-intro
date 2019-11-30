package expression;

public class Multiply extends BinaryOperation {
    protected Multiply(Expression arg1, Expression arg2) {
        super(arg1, arg2);
    }

    @Override
    public int evaluate(int x) {
        return arg1.evaluate(x) * arg2.evaluate(x);
    }

    @Override
    protected String getOperation() {
        return "*";
    }
}
