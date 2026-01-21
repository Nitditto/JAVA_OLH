package task3.src.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Customer {

  private String fullName;
  private String address;
  private String phoneNumber;
  private BigDecimal accountBalance;

  private final ArrayList<Vehicle> purchasedVehicles;

  private static final int MIN_PLATINUM_PURCHASES = 10;
  private static final int MIN_GOLD_PURCHASES = 5;
  private static final int MIN_SILVER_PURCHASES = 3;

  public Customer(String fullName, String address, String phoneNumber, BigDecimal accountBalance) {
    this.fullName = fullName;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.accountBalance = accountBalance;
    this.purchasedVehicles = new ArrayList<>();
  }

  public String getName() {
    return fullName;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phoneNumber;
  }

  public BigDecimal getBalance() {
    return accountBalance;
  }

  public void setBalance(BigDecimal balance) {
    this.accountBalance = balance;
  }

  public void addPurchase(Vehicle vehicle) {
    purchasedVehicles.add(vehicle);
  }

  public ArrayList<Vehicle> getPurchasedVehicles() {
    return purchasedVehicles;
  }

  public LoyaltyLevel getLoyaltyLevel() {
    int count = purchasedVehicles.size();

    if (count >= MIN_PLATINUM_PURCHASES) {
      return LoyaltyLevel.PLATINUM;
    }
    if (count >= MIN_GOLD_PURCHASES) {
      return LoyaltyLevel.GOLD;
    }
    if (count >= MIN_SILVER_PURCHASES) {
      return LoyaltyLevel.SILVER;
    }

    return LoyaltyLevel.REGULAR;
  }
}
