package structure;

public class Identifier implements Expression {
    private final String name;

    public Identifier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int evaluate() {
        return 0;
    }
}
