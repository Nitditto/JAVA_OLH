
import java.math.BigDecimal;
import java.util.Scanner;

class Motorcycle extends Vehicle {

  private static final BigDecimal IMPORT_TAX_RATE = new BigDecimal("0.30");
  private static final BigDecimal SPECIAL_TAX_RATE = new BigDecimal("0.20");
  private static final double CAPACITY = 150.0;

  private double cylinderCapacity;
  private String type;
  private int power;

  public Motorcycle() {
  }

  @Override
  public void inputInfo(Scanner sc) {
    super.inputInfo(sc);
    cylinderCapacity = getValidDouble(sc, "Engine Capacity (cc)");

    System.out.println("Select Motorcycle Type:");
    System.out.println("  1. Manual (Xe so)");
    System.out.println("  2. Automatic (Tay ga)");

    int typeChoice;
    do {
      typeChoice = getValidInteger(sc, "Choose Type (1-2)");
    } while (typeChoice != 1 && typeChoice != 2);

    type = (typeChoice == 1) ? "Manual" : "Automatic";
    power = getValidInteger(sc, "Power (HP/kW)");
  }

  @Override
  public BigDecimal calculateImportTax() {
    return origin ? basePrice.multiply(IMPORT_TAX_RATE) : BigDecimal.ZERO;
  }

  @Override
  public BigDecimal calculateSpecialTax() {
    if (cylinderCapacity <= CAPACITY) {
      return BigDecimal.ZERO;
    } else {
      BigDecimal baseWithImport = basePrice.add(calculateImportTax());
      return baseWithImport.multiply(SPECIAL_TAX_RATE);
    }
  }
}
