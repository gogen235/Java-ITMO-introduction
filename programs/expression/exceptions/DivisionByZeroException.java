package expression.exceptions;

public class DivisionByZeroException extends ArithmeticException {
    DivisionByZeroException(String massage) {
        super(massage);
    }
}
