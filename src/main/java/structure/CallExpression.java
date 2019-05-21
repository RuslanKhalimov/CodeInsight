package structure;

import exceptions.ArgumentNumberMissmatchException;
import exceptions.FunctionNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CallExpression implements Expression {
    private final Identifier identifier;
    private final List<Expression> argumentList;

    public CallExpression(Identifier identifier, List<Expression> argumentList) {
        this.identifier = identifier;
        this.argumentList = argumentList;
    }

    public List<Expression> getArgumentList() {
        return argumentList;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        StringBuilder argumentListString = new StringBuilder(argumentList.get(0).toString());
        for (int i = 1; i < argumentList.size(); i++) {
            argumentListString.append(",").append(argumentList.get(i).toString());
        }
        return identifier.getName() + "(" + argumentListString.toString() + ")";
    }

    @Override
    public int evaluate(Map<String, Integer> values, Map<String, Map<Integer, FunctionDefinition>> functionDefinitions, int line) throws Exception {
        Map<Integer, FunctionDefinition> functionsWithSameIdentifier = functionDefinitions.get(identifier.getName());
        if (functionsWithSameIdentifier == null) {
            throw new FunctionNotFoundException(identifier.getName(), line);
        }

        FunctionDefinition function = functionsWithSameIdentifier.get(argumentList.size());
        if (function == null) {
            throw new ArgumentNumberMissmatchException(identifier.getName(), line);
        }

        Map<String, Integer> argumentValues = new HashMap<>();
        for (int i = 0; i < argumentList.size(); i++) {
            argumentValues.put(function.getParameterList().get(i).getName(), argumentList.get(i).evaluate(values, functionDefinitions, line));
        }
        return function.getExpression().evaluate(argumentValues, functionDefinitions, function.getLine());
    }
}
