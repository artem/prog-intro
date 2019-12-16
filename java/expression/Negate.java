package expression;

public class Negate extends UnaryOperation {
    public Negate(CommonExpression arg) {
        super(arg);
    }

    @Override
    public int calculate(int a) {
        return -a;
    }

    @Override
    public double calculate(double a) {
        return -a;
    }

    @Override
    protected String getOperation() {
        return "-";
    }
}
