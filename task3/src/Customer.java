
import java.math.BigDecimal;
import java.util.ArrayList;

class Customer {

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

  public LoyaltyLevel getLoyaltyLevel() {
    int count = purchasedVehicles.size();

    if (count >= MIN_PLATINUM_PURCHASES)
      return LoyaltyLevel.PLATINUM;
    if (count >= MIN_GOLD_PURCHASES)
      return LoyaltyLevel.GOLD;
    if (count >= MIN_SILVER_PURCHASES)
      return LoyaltyLevel.SILVER;

    return LoyaltyLevel.REGULAR;
  }

  public void addPurchase(Vehicle vehicle) {
    purchasedVehicles.add(vehicle);
  }

  public boolean paySuccess(BigDecimal amount) {
    if (accountBalance.compareTo(amount) >= 0) {
      accountBalance = accountBalance.subtract(amount);
      return true;
    }
    return false;
  }

  public void displayCustomerInformation() {
    System.out.println("Name: " + fullName);
    System.out.println("Address: " + address);
    System.out.println("Phone: " + phoneNumber);
    System.out.printf("Balance: %,15.0f VND\n", accountBalance);
    System.out.println("Loyalty Level: " + getLoyaltyLevel());
    System.out.println("Owned Vehicles: " + purchasedVehicles.size());

    if (!purchasedVehicles.isEmpty()) {
      System.out.print("History: ");
      for (int i = 0; i < purchasedVehicles.size(); i++) {
        Vehicle vehicle = purchasedVehicles.get(i);
        System.out.printf(" %d. %s %s (%d)\n",
            (i + 1), vehicle.brand, vehicle.modelName, vehicle.productionYear);
      }
    }
  }
}
