package structure;

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
        return left.toString() + operation + right.toString();
    }

    @Override
    public int evaluate() {
        switch (operation) {
            case "+":
                return left.evaluate() + right.evaluate();
            case "-":
                return left.evaluate() - right.evaluate();
            case "*":
                return left.evaluate() * right.evaluate();
            case "/":
                return left.evaluate() / right.evaluate();
            case "%":
                return left.evaluate() % right.evaluate();
            case ">":
                return left.evaluate() > right.evaluate() ? 1 : 0;
            case "<":
                return left.evaluate() < right.evaluate() ? 1 : 0;
            case "=":
                return left.evaluate() == right.evaluate() ? 1 : 0;
            default:
                throw new RuntimeException("invalid operator");
        }
    }
}
