package structure;

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
    public int evaluate(Map<String, Integer> values, Map<String, Map<Integer, FunctionDefinition>> functionDefinitions) throws Exception {
        Map<Integer, FunctionDefinition> functionsWithSameIdentifier = functionDefinitions.get(identifier.getName());
        if (functionsWithSameIdentifier == null) {
            throw new Exception("FUNCTION NOT FOUND");
        }

        FunctionDefinition function = functionsWithSameIdentifier.get(argumentList.size());
        if (function == null) {
            throw new Exception("ARGUMENT NUMBER MISMATCH");
        }

        Map<String, Integer> argumentValues = new HashMap<>();
        for (int i = 0; i < argumentList.size(); i++) {
            argumentValues.put(function.getParameterList().get(i).getName(), argumentList.get(i).evaluate(values, functionDefinitions));
        }
        return function.getExpression().evaluate(argumentValues, functionDefinitions);
    }
}
