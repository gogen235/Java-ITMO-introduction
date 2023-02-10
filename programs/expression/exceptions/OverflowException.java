package expression.exceptions;

public class OverflowException extends ArithmeticException {
    OverflowException(String massage) {
        super(massage);
    }
}
