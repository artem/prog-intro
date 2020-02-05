package expression.exceptions;

import expression.CommonExpression;

public class CheckedNegate extends expression.Negate {
    public CheckedNegate(CommonExpression vx) {
        super(vx);
    }

    @Override
    protected int calculate(int a) {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException(this);
        }
        return super.calculate(a);
    }
}
