package expression;

import expression.exceptions.CalculateException;

public class LeftShift extends BinaryOperation {
    public LeftShift(CommonExpression arg1, CommonExpression arg2) {
        super(arg1, arg2);
    }

    @Override
    protected double calculate(double a, double b) {
        throw new CalculateException("Bitwise shift is not defined for double", this);
    }

    @Override
    public int calculate(int a, int b) {
        return a << b;
    }

    @Override
    protected String getOperation() {
        return "<<";
    }
}
