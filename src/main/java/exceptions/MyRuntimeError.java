package exceptions;

import structure.Expression;

public class MyRuntimeError extends Exception {
    public MyRuntimeError(String expression, int line) {
        super("RUNTIME ERROR " + expression + ":" + line);
    }
}
