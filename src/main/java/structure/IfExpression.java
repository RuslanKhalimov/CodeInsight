package structure;

public class IfExpression implements Expression {
    private final Expression condition;
    private final Expression thenClause;
    private final Expression elseClause;

    public IfExpression(Expression condition, Expression thenClause, Expression elseClause) {
        this.condition = condition;
        this.thenClause = thenClause;
        this.elseClause = elseClause;
    }

    public Expression getElseClause() {
        return elseClause;
    }

    public Expression getThenClause() {
        return thenClause;
    }

    public Expression getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return "[" + condition.toString() + "]?{" + thenClause.toString() + "}:{" + elseClause + "}";
    }

    @Override
    public int evaluate() {
        if (condition.evaluate() == 0) {
            return elseClause.evaluate();
        }
        return thenClause.evaluate();
    }
}
