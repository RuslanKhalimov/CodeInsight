package exceptions;

public class FunctionNotFoundException extends Exception {
    public FunctionNotFoundException(String name, int line) {
        super("FUNCTION NOT FOUND " + name + ":" + line);
    }
}
