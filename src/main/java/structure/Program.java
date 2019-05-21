package structure;

import java.util.HashMap;
import java.util.Map;

public class Program {
    private final Map<String, Map<Integer, FunctionDefinition>> functionDefinitionList;
    private final Expression expression;
    private final int line;

    public Program(Map<String, Map<Integer, FunctionDefinition>> functionDefinitionList, Expression expression, int line) {
        this.functionDefinitionList = functionDefinitionList;
        this.expression = expression;
        this.line = line;
    }

    public Expression getExpression() {
        return expression;
    }

    public Map<String, Map<Integer, FunctionDefinition>> getFunctionDefinitionList() {
        return functionDefinitionList;
    }

    public int runProgram() throws Exception {
        return expression.evaluate(new HashMap<>(), functionDefinitionList, line);
    }
}
