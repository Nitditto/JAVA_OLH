package task3.src.model;

import java.math.BigDecimal;

public class Car extends Vehicle {

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

  public void setSeatCount(int seatCount) {
    this.seatCount = seatCount;
  }

  public int getSeatCount() {
    return seatCount;
  }

  public void setFuelType(String fuelType) {
    this.fuelType = fuelType;
  }

  public String getFuelType() {
    return fuelType;
  }

  public void setCylinderCapacity(double cylinderCapacity) {
    this.cylinderCapacity = cylinderCapacity;
  }

  public double getCylinderCapacity() {
    return cylinderCapacity;
  }

  public void setBodyType(String bodyType) {
    this.bodyType = bodyType;
  }

  public String getBodyType() {
    return bodyType;
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