package structure;

import java.util.Map;

public class Program {
    private final Map<String, Map<Integer, FunctionDefinition>> functionDefinitionList;
    private final Expression expression;

    public Program(Map<String, Map<Integer, FunctionDefinition>> functionDefinitionList, Expression expression) {
        this.functionDefinitionList = functionDefinitionList;
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public Map<String, Map<Integer, FunctionDefinition>> getFunctionDefinitionList() {
        return functionDefinitionList;
    }
}
