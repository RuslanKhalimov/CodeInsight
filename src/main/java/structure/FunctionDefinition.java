package structure;

import java.util.List;

public class FunctionDefinition {
    private final Identifier identifier;
    private final List<Identifier> parameterList;
    private final Expression expression;
    private final int line;

    public FunctionDefinition(Identifier identifier, List<Identifier> parameterList, Expression expression, int line) {
        this.identifier = identifier;
        this.parameterList = parameterList;
        this.expression = expression;
        this.line = line;
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

    public int getLine() {
        return line;
    }
}
