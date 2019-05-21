package structure;

import java.util.List;

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
    public int evaluate() {
        return 0;
    }
}
