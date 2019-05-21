package structure;

import java.util.List;

public class FunctionDefinition {
    private final Identifier identifier;
    private final List<Identifier> parameterList;
    private final Expression expression;

    public FunctionDefinition(Identifier identifier, List<Identifier> parameterList, Expression expression) {
        this.identifier = identifier;
        this.parameterList = parameterList;
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public List<Identifier> getParameterList() {
        return parameterList;
    }

    public Identifier getIdentifier() {
        return identifier;
    }
}
