package task3.src.exception;

import java.math.BigDecimal;
import task3.src.constant.MessageConstant;

public class InsufficientBalanceException extends BaseException {
    public InsufficientBalanceException(String message) {
        super(message);
    }

    public static void checkNotEnoughBalance(BigDecimal balance, BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            BigDecimal missing = amount.subtract(balance);
            throw new InsufficientBalanceException(
                    MessageConstant.ERR_NOT_ENOUGH_BALANCE + " Missing: " + String.format("%,.0f", missing));
        }
    }
}