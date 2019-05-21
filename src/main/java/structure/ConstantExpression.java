package structure;

public class ConstantExpression implements Expression {
    private final int number;

    public ConstantExpression(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int evaluate() {
        return number;
    }
}
