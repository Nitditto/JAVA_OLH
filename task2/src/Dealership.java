import java.util.ArrayList;

class Dealership {

  private String name;
  private ArrayList<InventoryItem> inventories = new ArrayList<>();
  private ArrayList<Customer> customers = new ArrayList<>();

  public Dealership(String name) {
    this.name = name;
  }

  public void addVehicleToStock(Vehicle vehicle, int quantity) {
    this.inventories.add(new InventoryItem(vehicle, quantity));
  }

  public void addCustomer(Customer customer) {
    this.customers.add(customer);
  }

  public void displayInventory() {
    if (inventories.isEmpty()) {
      System.out.println("There is no inventory items in this dealership!");
      return;
    }
    for (int i = 0; i < inventories.size(); i++) {
      inventories.get(i).getVehicle().displayInfo();
      System.out.println("Stock Quantity: " + inventories.get(i).getQuantity());
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
}
