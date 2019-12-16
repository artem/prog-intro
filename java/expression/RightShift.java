package expression;

public class RightShift extends BinaryOperation {
    public RightShift(CommonExpression arg1, CommonExpression arg2) {
        super(arg1, arg2);
    }

    @Override
    protected double calculate(double a, double b) {
        throw new IllegalArgumentException("Bitwise shift is not defined for double");
    }

    @Override
    public int calculate(int a, int b) {
        return a >> b;
    }

    @Override
    protected String getOperation() {
        return ">>";
    }
}
