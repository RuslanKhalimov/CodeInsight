package structure;

import java.util.List;

public class FunctionDefinition {
    private final List<Identifier> parameterList;
    private final Expression expression;

    public FunctionDefinition(Identifier identifier, List<Identifier> parameterList, Expression expression) {
        this.parameterList = parameterList;
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public List<Identifier> getParameterList() {
        return parameterList;
    }
}
