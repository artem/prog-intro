package expression;

public class Multiply extends BinaryOperation {
    protected Multiply(Evaluable arg1, Evaluable arg2) {
        super(arg1, arg2);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return arg1.evaluate(x, y, z) * arg2.evaluate(x, y, z);
    }

    @Override
    public double evaluate(double x) {
        return arg1.evaluate(x) * arg2.evaluate(x);
    }

    @Override
    protected String getOperation() {
        return "*";
    }
}
