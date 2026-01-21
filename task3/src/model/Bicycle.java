package task3.src.model;

import java.math.BigDecimal;

public class Bicycle extends Vehicle {

  private static final BigDecimal IMPORT_TAX_RATE = new BigDecimal("0.10");
  private String type;
  private String material;

  public Bicycle() {
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setMaterial(String material) {
    this.material = material;
  }

  public String getMaterial() {
    return material;
  }

  @Override
  public BigDecimal calculateImportTax() {
    return origin ? basePrice.multiply(IMPORT_TAX_RATE) : BigDecimal.ZERO;
  }

  @Override
  public BigDecimal calculateSpecialTax() {
    return BigDecimal.ZERO;
  }
}
