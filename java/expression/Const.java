package expression;

public class Const implements Expression {
    private final int value;

    public Const(int value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Const) {
            Const second = (Const) obj;
            return value == second.value;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}
