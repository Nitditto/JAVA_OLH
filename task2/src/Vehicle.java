
import java.math.BigDecimal;
import java.util.Scanner;

abstract class Vehicle {

  protected String modelName;
  protected String brand;
  protected int productionYear;
  protected BigDecimal basePrice;
  protected boolean origin;

  public static final BigDecimal VAT_RATE = new BigDecimal("0.10");
  public static final int ORIGIN_DOMESTIC = 1;
  public static final int ORIGIN_IMPORTED = 2;

  public Vehicle() {
  }

  public abstract BigDecimal calculateImportTax();

  public abstract BigDecimal calculateSpecialTax();

  public BigDecimal totalPrice() {
    BigDecimal importTax = calculateImportTax();
    BigDecimal specialTax = calculateSpecialTax();
    BigDecimal totalBeforeVAT = basePrice.add(importTax).add(specialTax);
    BigDecimal vatAmount = totalBeforeVAT.multiply(VAT_RATE);
    return totalBeforeVAT.add(vatAmount);
  }

  public void inputInfo(Scanner sc) {
    System.out.print("Name Model: ");
    modelName = sc.nextLine();
    System.out.print("Brand: ");
    brand = sc.nextLine();
    productionYear = getValidInteger(sc, "Production Year");
    basePrice = getValidBigDecimal(sc, "Base Price");
    System.out.println("Origin:");
    System.out.println("  1. Domestic (Trong nuoc)");
    System.out.println("  2. Imported (Nhap khau)");
    System.out.printf("Origin (%d: Domestic, %d: Imported): ", ORIGIN_DOMESTIC, ORIGIN_IMPORTED);
    
    int originChoice;
    do {
      originChoice = getValidInteger(sc, "Choose Origin (1-2)");
      if (originChoice != 1 && originChoice != 2) {
        System.out.println("Invalid choice! Please enter 1 or 2.");
      }
    } while (originChoice != 1 && originChoice != 2);
    origin = (originChoice == ORIGIN_IMPORTED);
  }

  public void displayInfo() {
    System.out.println("Type vehicle: " + this.getClass().getSimpleName());
    System.out.println("Model: " + modelName + " | Brand: " + brand);
    System.out.println(
        "Year: " + productionYear + " | Origin: " + (origin ? "Imported" : "Domestic"));
    System.out.printf("Base Price: %,15.0f VND\n", basePrice);
    System.out.printf("Import Tax: %,15.0f VND\n", calculateImportTax());
    System.out.printf("Special Tax: %,15.0f VND\n", calculateSpecialTax());
    System.out.printf(">> Total Price: %,15.0f VND\n", totalPrice());
  }

  protected int getValidInteger(Scanner sc, String title) {
    while (true) {
      try {
        System.out.print(title + ": ");
        return Integer.parseInt(sc.nextLine().trim());
      } catch (NumberFormatException e) {
        System.out.println("Error: Please enter a valid integer number!");
      }
    }
  }

  protected double getValidDouble(Scanner sc, String title) {
    while (true) {
      try {
        System.out.print(title + ": ");
        return Double.parseDouble(sc.nextLine().trim());
      } catch (NumberFormatException e) {
        System.out.println("Error: Please enter a valid number!");
      }
    }
  }

  protected BigDecimal getValidBigDecimal(Scanner sc, String title) {
    while (true) {
      try {
        System.out.print(title + ": ");
        return new BigDecimal(sc.nextLine().trim());
      } catch (Exception e) {
        System.out.println("Error: Please enter a valid amount!");
      }
    }
  }
}