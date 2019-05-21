package structure;

import java.util.Map;

public class Identifier implements Expression {
    private final String name;

    public Identifier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int evaluate(Map<String, Integer> values, Map<String, Map<Integer, FunctionDefinition>> functionDefinitions) {
        return values.get(name);
    }
}
