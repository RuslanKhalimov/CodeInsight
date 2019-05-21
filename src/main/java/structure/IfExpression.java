package structure;

import java.util.Map;

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
    public int evaluate(Map<String, Integer> values, Map<String, Map<Integer, FunctionDefinition>> functionDefinitions) throws Exception {
        if (condition.evaluate(values, functionDefinitions) == 0) {
            return elseClause.evaluate(values, functionDefinitions);
        }
        return thenClause.evaluate(values, functionDefinitions);
    }
}
