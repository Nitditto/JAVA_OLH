
import java.math.BigDecimal;
import java.util.Scanner;

class Car extends Vehicle {

  private static final BigDecimal IMPORT_TAX_RATE = new BigDecimal("0.50");
  private static final BigDecimal SPECIAL_TAX_LOW_RATE = new BigDecimal("0.50");
  private static final BigDecimal SPECIAL_TAX_HIGH_RATE = new BigDecimal("1.00");
  private static final double CAPACITY = 3000.0;
  private int seatCount;
  private String fuelType;
  private double cylinderCapacity;
  private String bodyType;

  public Car() {
  }

  @Override
  public void inputInfo(Scanner sc) {
    super.inputInfo(sc);
    seatCount = getValidInteger(sc, "Seat Count");
    System.out.println("Select Fuel Type:");
    System.out.println("  1. Gasoline (Xang)");
    System.out.println("  2. Diesel (Dau)");
    System.out.println("  3. Electric (Dien)");
    int fuelType;
    do {
      fuelType = getValidInteger(sc, "Choose Fuel (1-3)");
    } while (fuelType < 1 || fuelType > 3);
    cylinderCapacity = getValidDouble(sc, "Engine Capacity (cc)");

    System.out.print("Body Type (Ex: Sedan, SUV, Truck...): ");
    bodyType = sc.nextLine();
  }

  @Override
  public BigDecimal calculateImportTax() {
    if (!origin) {
      return BigDecimal.ZERO;
    }
    return basePrice.multiply(IMPORT_TAX_RATE);
  }

  @Override
  public BigDecimal calculateSpecialTax() {
    BigDecimal baseWithImport = basePrice.add(calculateImportTax());
    return (cylinderCapacity < CAPACITY)
        ? baseWithImport.multiply(SPECIAL_TAX_LOW_RATE)
        : baseWithImport.multiply(SPECIAL_TAX_HIGH_RATE);
  }
}