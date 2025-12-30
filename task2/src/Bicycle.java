
import java.math.BigDecimal;
import java.util.Scanner;

class Bicycle extends Vehicle {

  private static final BigDecimal IMPORT_TAX_RATE = new BigDecimal("0.10");
  private String type;
  private String material;

  public Bicycle() {
  }

  @Override
  public void inputInfo(Scanner sc) {
    super.inputInfo(sc);
    System.out.print("Bicycle Type (Ex: Mountain, Road, Hybrid): ");
    type = sc.nextLine();
    System.out.print("Frame Material (Ex: Carbon, Aluminum, Steel): ");
    material = sc.nextLine();
  }

  @Override
  public BigDecimal calculateImportTax() {
    return isImported ? basePrice.multiply(IMPORT_TAX_RATE) : BigDecimal.ZERO;
  }

  @Override
  public BigDecimal calculateSpecialTax() {
    return BigDecimal.ZERO;
  }
}
