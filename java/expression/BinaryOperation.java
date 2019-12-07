package expression;

import java.util.Objects;

public abstract class BinaryOperation extends CommonExpression {
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
        if (obj instanceof BinaryOperation) {
            BinaryOperation second = (BinaryOperation) obj;
            return Objects.equals(arg1, second.arg1) && Objects.equals(arg2, second.arg2)
                    && Objects.equals(getOperation(), second.getOperation());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (Objects.hashCode(arg1) * 31 + Objects.hashCode(arg2)) * 31 + Objects.hashCode(getOperation());
    }

    protected abstract String getOperation();
}
