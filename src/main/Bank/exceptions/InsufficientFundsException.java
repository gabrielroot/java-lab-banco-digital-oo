package exceptions;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException() {
        super("Saldo insuficiente.");
    }
}
