package task3.src.model;

import java.math.BigDecimal;

public class Motorcycle extends Vehicle {

  private static final BigDecimal IMPORT_TAX_RATE = new BigDecimal("0.30");
  private static final BigDecimal SPECIAL_TAX_RATE = new BigDecimal("0.20");
  private static final double CAPACITY = 150.0;

  private double cylinderCapacity;
  private String type;
  private int power;

  public Motorcycle() {
  }

  public void setCylinderCapacity(double cylinderCapacity) {
    this.cylinderCapacity = cylinderCapacity;
  }

  public double getCylinderCapacity() {
    return cylinderCapacity;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setPower(int power) {
    this.power = power;
  }

  public int getPower() {
    return power;
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
