import java.math.BigDecimal;
import java.util.ArrayList;

class Dealership {

  private String name;
  private ArrayList<InventoryItem> inventory = new ArrayList<>();
  private ArrayList<Customer> customers = new ArrayList<>();

  public Dealership(String name) {
    this.name = name;
  }

  public void addVehicleToStock(Vehicle vehicle, int quantity) {
    inventory.add(new InventoryItem(vehicle, quantity));
  }

  public void addCustomer(Customer customer) {
    this.customers.add(customer);
  }

  public ArrayList<Customer> getCustomers() {
    return this.customers;
  }

  public ArrayList<InventoryItem> getInventory() {
    return this.inventory;
  }

  public void displayInventory() {
    if (inventory.isEmpty()) {
      System.out.println("There is no inventory items in this dealership!");
      return;
    }
    for (int i = 0; i < inventory.size(); i++) {
      inventory.get(i).getVehicle().displayInfo();
      System.out.println("Stock Quantity: " + inventory.get(i).getQuantity());
    }
  }

  public void displayCustomers() {
    if (customers.isEmpty()) {
      System.out.println("No customer found!");
      return;
    }
    for (int i = 0; i < customers.size(); i++) {
      customers.get(i).displayCustomerInformation();
    }
  }

  private InventoryItem findItem(String modelName, String brand) {
    for (InventoryItem item : inventory) {
      Vehicle vehicle = item.getVehicle();
      if (vehicle.modelName.equalsIgnoreCase(modelName) && vehicle.brand.equalsIgnoreCase(brand)) {
        return item;
      }
    }
    return null;
  }

  private void suggestAlternative(Class<? extends Vehicle> type) {
    System.out.println("Similar vehicles in stock:");
    boolean found = false;
    for (InventoryItem item : inventory) {
      Vehicle vehicle = item.getVehicle();
      if (type.isInstance(vehicle) && item.getQuantity() > 0) {
        System.out.printf("- %s %s (Price: %,.0f VND)\n", vehicle.getBrand(), vehicle.getModelName(),
            vehicle.totalPrice());
        found = true;
      }
    }
    if (!found)
      System.out.println("No similar vehicles available");
  }

  public void sellVehicle(Customer customer, String modelName, String brand) {
    InventoryItem item = findItem(modelName, brand);
    if (item == null || item.getQuantity() <= 0) {
      System.out.println("Vehicle not found or out of stock!");
      if (item != null) {
        suggestAlternative(item.getVehicle().getClass());
      }
      return;
    }

    Vehicle vehicle = item.getVehicle();
    BigDecimal basePrice = vehicle.totalPrice();
    LoyaltyLevel level = customer.getLoyaltyLevel();
    BigDecimal discountRate = level.getDiscountRate();
    BigDecimal discountAmount = basePrice.multiply(discountRate);
    BigDecimal sellPrice = basePrice.subtract(discountAmount);
    System.out.println("\nTransaction:");

    System.out.println("Vehicle " + vehicle.brand + " " + vehicle.getModelName());

    System.out.printf("Price: %,.0f VND\n", basePrice);
    System.out.printf("Loyalty Discount (%s - %.0f%%): -%,.0f VND\n",
        level, discountRate.multiply(new BigDecimal(100)), discountAmount);
    System.out.printf("Total to pay: %,.0f VND\n", sellPrice);

    if (customer.paySuccess(sellPrice)) {
      item.decreaseQuantity();
      customer.addPurchase(vehicle);

      System.out.println("Vehicle purchased successfully!");
      System.out.printf("New Balance: %,.0f VND\n", customer.getBalance());
      System.out.println("Your Loyalty Level is now: " + customer.getLoyaltyLevel());
    } else {
      System.out.println("Insufficient balance!");
      System.out.printf("You need %,.0f VND more\n", sellPrice.subtract(customer.getBalance()));

      suggestAlternative(vehicle.getClass());
    }
  }
}
