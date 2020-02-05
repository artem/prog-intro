package expression.exceptions;

import expression.CommonExpression;

public abstract class EvaluateException extends ExpressionException {
    public EvaluateException(String err, CommonExpression expr) {
        super(err + ": " + expr);
    }
}
