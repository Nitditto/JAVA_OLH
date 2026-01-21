package task3.src.model;

import java.math.BigDecimal;

public abstract class Vehicle {

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

  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  public String getModelName() {
    return modelName;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getBrand() {
    return brand;
  }

  public void setProductionYear(int year) {
    this.productionYear = year;
  }

  public int getProductionYear() {
    return productionYear;
  }

  public void setBasePrice(BigDecimal price) {
    this.basePrice = price;
  }

  public BigDecimal getBasePrice() {
    return basePrice;
  }

  public void setOrigin(boolean origin) {
    this.origin = origin;
  }

  public boolean isImported() {
    return origin;
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

}