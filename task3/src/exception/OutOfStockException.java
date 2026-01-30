package task3.src.exception;

public class OutOfStockException extends StoreException {
  public OutOfStockException(String message) {
    super(message);
  }
}