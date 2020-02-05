package expression.exceptions;

public class Playground {
    public static void main(String[] args) {
        //try
        new ExpressionParser().parse("5 / 0").evaluate(1510716961, 852358369, -1538579178);
        //catch expressionexception
    }
}
