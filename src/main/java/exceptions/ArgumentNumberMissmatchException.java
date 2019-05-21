package exceptions;

public class ArgumentNumberMissmatchException extends Exception {
    public ArgumentNumberMissmatchException(String name, int line) {
        super("ARGUMENT NUMBER MISMATCH " + name + ":" + line);
    }
}
