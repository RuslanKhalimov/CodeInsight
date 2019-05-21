package structure;

import exceptions.ParameterNotFoundException;

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
    public String toString() {
        return name;
    }

    @Override
    public int evaluate(Map<String, Integer> values, Map<String, Map<Integer, FunctionDefinition>> functionDefinitions, int line) throws ParameterNotFoundException {
        if (!values.containsKey(name)) {
            throw new ParameterNotFoundException(name, line);
        }
        return values.get(name);
    }
}
