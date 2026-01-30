package task3.src.exception;

public class InsufficientBalanceException extends StoreException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}