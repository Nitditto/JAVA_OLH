package task3.src.utils;

import java.math.BigDecimal;
import java.util.Scanner;
import task3.src.constant.MessageConstant;

public class InputHelper {

  private static final Scanner sc = new Scanner(System.in);

  public static int getValidInteger(String prompt) {
    while (true) {
      try {
        System.out.print(prompt + ": ");
        return Integer.parseInt(sc.nextLine());
      } catch (NumberFormatException e) {
        System.out.println(MessageConstant.ERR_INVALID_INTEGER);
      }
    }
  }

  public static double getValidDouble(String prompt) {
    while (true) {
      try {
        System.out.print(prompt + ": ");
        return Double.parseDouble(sc.nextLine().trim());
      } catch (NumberFormatException e) {
        System.out.println(MessageConstant.ERR_INVALID_DOUBLE);
      }
    }
  }

  public static BigDecimal getValidBigDecimal(String prompt) {
    while (true) {
      try {
        System.out.print(prompt + ": ");
        return new BigDecimal(sc.nextLine().trim());
      } catch (Exception e) {
        System.out.println(MessageConstant.ERR_INVALID_AMOUNT);
      }
    }
  }

  public static String getString(String prompt) {
    System.out.print(prompt + ": ");
    return sc.nextLine().trim();
  }
}
