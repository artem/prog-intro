package expression;

import java.util.Objects;

public class Variable implements Expression {
    private final String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Variable) {
            Variable second = (Variable) obj;
            return Objects.equals(variable, second.variable);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(variable);
    }
}
