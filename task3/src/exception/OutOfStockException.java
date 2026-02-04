package task3.src.exception;

import task3.src.constant.MessageConstant;

public class OutOfStockException extends BaseException {
  public OutOfStockException(String message) {
    super(message);
  }

  public static void checkOutOfStock(int quantity) {
    if (quantity <= 0) {
      throw new OutOfStockException(MessageConstant.ERR_OUT_OF_STOCK);
    }
  }
}