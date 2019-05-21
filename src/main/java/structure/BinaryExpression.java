package structure;

import exceptions.MyRuntimeError;

import java.util.Map;

public class BinaryExpression implements Expression {
    private final Expression left;
    private final Expression right;
    private final String operation;

    public BinaryExpression(Expression left, Expression right, String operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + operation + right.toString() + ")";
    }

    @Override
    public int evaluate(Map<String, Integer> values, Map<String, Map<Integer, FunctionDefinition>> functionDefinitions, int line) throws Exception {
        try {
            switch (operation) {
                case "+":
                    return left.evaluate(values, functionDefinitions, line) + right.evaluate(values, functionDefinitions, line);
                case "-":
                    return left.evaluate(values, functionDefinitions, line) - right.evaluate(values, functionDefinitions, line);
                case "*":
                    return left.evaluate(values, functionDefinitions, line) * right.evaluate(values, functionDefinitions, line);
                case "/":
                    return left.evaluate(values, functionDefinitions, line) / right.evaluate(values, functionDefinitions, line);
                case "%":
                    return left.evaluate(values, functionDefinitions, line) % right.evaluate(values, functionDefinitions, line);
                case ">":
                    return left.evaluate(values, functionDefinitions, line) > right.evaluate(values, functionDefinitions, line) ? 1 : 0;
                case "<":
                    return left.evaluate(values, functionDefinitions, line) < right.evaluate(values, functionDefinitions, line) ? 1 : 0;
                case "=":
                    return left.evaluate(values, functionDefinitions, line) == right.evaluate(values, functionDefinitions, line) ? 1 : 0;
                default:
                    throw new Exception("invalid operator");
            }
        } catch (Exception ignored) {
            throw new MyRuntimeError(toString(), line);
        }
    }
}
