package structure;

import java.util.Map;

public class ConstantExpression implements Expression {
    private final int number;

    public ConstantExpression(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }

    @Override
    public int evaluate(Map<String, Integer> values, Map<String, Map<Integer, FunctionDefinition>> functionDefinitions, int line) {
        return number;
    }
}
