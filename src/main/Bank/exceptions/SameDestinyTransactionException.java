package exceptions;

public class SameDestinyTransactionException extends Exception {
    public SameDestinyTransactionException() {
        super("Não é possível fazer esta operação para a mesma conta de origem.");
    }
}
