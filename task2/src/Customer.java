
import java.math.BigDecimal;
import java.util.ArrayList;

class Customer {

  private String name;
  private String address;
  private String phone;
  private BigDecimal balance;

  private final ArrayList<Vehicle> purchaseHistory;
  // private ArrayList<Customer> customers;

  public Customer(String name, String address, String phone, BigDecimal balance) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.balance = balance;
    this.purchaseHistory = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public String getLoyaltyLevel() {
    int count = purchaseHistory.size();
    if (count >= 10)
      return "PLATINUM";
    else if (count >= 5)
      return "GOLD";
    else if (count >= 3)
      return "SILVER";
    return "REGULAR";
  }

  public void addPurchase(Vehicle vehicle) {
    purchaseHistory.add(vehicle);
  }

  public void displayCustomerInformation() {
    System.out.println("Name: " + name);
    System.out.println("Address: " + address);
    System.out.println("Phone: " + phone);
    System.out.printf("Balance: %,15.0f VND\n", balance);
    System.out.println("Loyalty Level: " + getLoyaltyLevel());
    System.out.println("Owned Vehicles: " + purchaseHistory.size());
    if (!purchaseHistory.isEmpty()) {
      System.out.print("History: ");
      for (Vehicle v : purchaseHistory)
        System.out.print(v.modelName + " ");
      System.out.println();
    }
  }
}
