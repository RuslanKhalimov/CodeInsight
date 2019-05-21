package structure;

import java.util.Map;

public interface Expression {
    int evaluate(Map<String, Integer> values, Map<String, Map<Integer, FunctionDefinition>> functionDefinitions, int line) throws Exception;
}
