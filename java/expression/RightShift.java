package expression;

public class RightShift extends BinaryOperation {
    public RightShift(CommonExpression arg1, CommonExpression arg2) {
        super(arg1, arg2);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return arg1.evaluate(x, y, z) >> arg2.evaluate(x, y, z);
    }

    @Override
    public double evaluate(double x) {
        throw new IllegalArgumentException("Bitwise shift is not defined for double");
    }

    @Override
    protected String getOperation() {
        return ">>";
    }
}
