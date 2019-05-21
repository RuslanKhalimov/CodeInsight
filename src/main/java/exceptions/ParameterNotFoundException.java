package exceptions;

public class ParameterNotFoundException extends Exception {
    public ParameterNotFoundException(String name, int line) {
        super("PARAMETER NOT FOUND " + name + ":" + line);
    }
}
