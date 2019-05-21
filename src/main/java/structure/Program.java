package structure;

import java.util.List;

public class Program {
    private final List<FunctionDefinition> functionDefinitionList;
    private final Expression expression;

    public Program(List<FunctionDefinition> functionDefinitionList, Expression expression) {
        this.functionDefinitionList = functionDefinitionList;
        this.expression = expression;
    }

    public void addFunctionDefinition(FunctionDefinition functionDefinition) {
        functionDefinitionList.add(functionDefinition);
    }

    public Expression getExpression() {
        return expression;
    }

    public List<FunctionDefinition> getFunctionDefinitionList() {
        return functionDefinitionList;
    }
}
