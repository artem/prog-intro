package expression;

public class Const extends Evaluable {
    private final double value;
    private final boolean integer;

    public Const(int value) {
        this.value = value;
        this.integer = true;
    }

    public Const(double value) {
        this.value = value;
        this.integer = false;
    }

    @Override
    public int evaluate(int x) {
        return (int) value;
    }

    @Override
    public double evaluate(double x) {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return evaluate(x);
    }

    @Override
    public String toString() {
        if (integer) {
            return String.valueOf((int)value);
        } else {
            return String.valueOf(value);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Const) {
            Const second = (Const) obj;
            return value == second.value && integer == second.integer;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }
}
