package expression;

import java.util.Objects;

public abstract class BinaryOperation extends Operation {
    protected final CommonExpression arg1;
    protected final CommonExpression arg2;

    protected BinaryOperation(CommonExpression arg1, CommonExpression arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(').append(arg1).append(' ').append(getOperation()).append(' ').append(arg2).append(')');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            BinaryOperation second = (BinaryOperation) obj;
            return Objects.equals(arg1, second.arg1) && Objects.equals(arg2, second.arg2);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (Objects.hashCode(arg1) * 31 + Objects.hashCode(arg2)) * 31 + Objects.hashCode(getClass());
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(arg1.evaluate(x, y, z), arg2.evaluate(x, y, z));
    }

    @Override
    public double evaluate(double x) {
        return calculate(arg1.evaluate(x), arg2.evaluate(x));
    }

    protected abstract double calculate(double a, double b);
    protected abstract int calculate(int a, int b);
}
