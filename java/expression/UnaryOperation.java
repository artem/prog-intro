package expression;

import java.util.Objects;

public abstract class UnaryOperation implements CommonExpression {
    protected final CommonExpression arg1;

    protected UnaryOperation(CommonExpression arg1) {
        this.arg1 = arg1;
    }

    @Override
    public int evaluate(int x) {
        return evaluate(x, 0, 0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getOperation()).append(arg1);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            UnaryOperation second = (UnaryOperation) obj;
            return Objects.equals(arg1, second.arg1);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(arg1) * 31 + Objects.hashCode(getOperation());
    }

    protected abstract String getOperation();
}
