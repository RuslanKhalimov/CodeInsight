package structure;

public class ConstantExpression implements Expression {
    private final Number number;
    private final boolean isNegative;

    public ConstantExpression(Number number, boolean isNegative) {
        this.number = number;
        this.isNegative = isNegative;
    }

    public boolean isNegative() {
        return isNegative;
    }

    public Number getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return isNegative ? "-" + number.getNumber() : number.getNumber();
    }
}
