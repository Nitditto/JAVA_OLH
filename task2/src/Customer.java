
import java.math.BigDecimal;
import java.util.ArrayList;

class Customer {

  private String fullName;
  private String address;
  private String phoneNumber;
  private BigDecimal accountBalance;

  private final ArrayList<Vehicle> purchaseHistory;

  private static final int MIN_PLATINUM_PURCHASES = 10;
  private static final int MIN_GOLD_PURCHASES = 5;
  private static final int MIN_SILVER_PURCHASES = 3;

  public Customer(String fullName, String address, String phoneNumber, BigDecimal accountBalance) {
    this.fullName = fullName;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.accountBalance = accountBalance;
    this.purchaseHistory = new ArrayList<>();
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
    int count = purchaseHistory.size();

    if (count >= MIN_PLATINUM_PURCHASES)
      return LoyaltyLevel.PLATINUM;
    if (count >= MIN_GOLD_PURCHASES)
      return LoyaltyLevel.GOLD;
    if (count >= MIN_SILVER_PURCHASES)
      return LoyaltyLevel.SILVER;

    return LoyaltyLevel.REGULAR;
  }

  public void addPurchase(Vehicle vehicle) {
    purchaseHistory.add(vehicle);
  }

  public void displayCustomerInformation() {
    System.out.println("Name: " + fullName);
    System.out.println("Address: " + address);
    System.out.println("Phone: " + phoneNumber);
    System.out.printf("Balance: %,15.0f VND\n", accountBalance);
    System.out.println("Loyalty Level: " + getLoyaltyLevel());
    System.out.println("Owned Vehicles: " + purchaseHistory.size());

    if (!purchaseHistory.isEmpty()) {
      System.out.print("History: ");
      for (int i = 0; i < purchaseHistory.size(); i++) {
        Vehicle v = purchaseHistory.get(i);
        System.out.printf(" %d. %s %s (%d)\n",
            (i + 1), v.brand, v.modelName, v.productionYear);
      }
    }
  }
}
