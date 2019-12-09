package expression;

public class Negate extends UnaryOperation {
    public Negate(CommonExpression arg) {
        super(arg);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -arg1.evaluate(x, y, z);
    }

    @Override
    public double evaluate(double x) {
        return -arg1.evaluate(x);
    }

    @Override
    protected String getOperation() {
        return "-";
    }
}
